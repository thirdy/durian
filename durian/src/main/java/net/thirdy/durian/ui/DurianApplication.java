/*
 * Copyright (C) 2015 thirdy
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.thirdy.durian.ui;

import static net.thirdy.durian.util.FileUtil.fromClasspathAsStream;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import net.thirdy.durian.Main;
import net.thirdy.durian.backend.BackendApi;
import net.thirdy.durian.model.Currency;
import net.thirdy.durian.model.Item;
import net.thirdy.durian.util.config.ItemNamesConfig;
import net.thirdy.durian.util.config.ItemWatchConfig;
import net.thirdy.durian.util.config.ItemWatchConfig.ItemWatch;

/**
 * @author thirdy
 *
 */
public class DurianApplication extends Application {
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	ObservableList<String> itemNamesList;
	ObservableList<ItemWatch> itemsToWatchList;
	
	TextArea consoleTextArea;
	Timer timer = new Timer();
	
	ItemWatchConfig itemWatchConfig = new ItemWatchConfig();
	ItemNamesConfig itemNamesConfig = new ItemNamesConfig();
	
	@Override
	public void start(Stage primaryStage) throws IOException, URISyntaxException {
		itemNamesList = FXCollections.observableArrayList(itemNamesConfig.loadNamesList());
		itemsToWatchList = FXCollections.observableArrayList(itemWatchConfig.loadItemWatchList());
		itemsToWatchList.addListener(new ListChangeListener<ItemWatch>() {

			@Override
			public void onChanged(ListChangeListener.Change<? extends ItemWatch> c) {
				itemWatchConfig.save(itemsToWatchList);
			}
		});
		
		String title = "Durian v0.1";
		primaryStage.setTitle(title);
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 800, 550);
		primaryStage.getIcons().add(loadImage("/images/48px-Durian.png"));
		
		// Center Pane
		BorderPane centerPane = new BorderPane();
		GridPane gridPane = setupCenterGridPane();
		
		Label itemsLbl = new Label("Items");
		GridPane.setHalignment(itemsLbl, HPos.CENTER);
		gridPane.add(itemsLbl, 0, 0);
		
		Label watchLbl = new Label("Watch");
		gridPane.add(watchLbl, 2, 0);
		GridPane.setHalignment(watchLbl, HPos.CENTER);
		
		ListView<String> itemNamesListView = new ListView<>(itemNamesList);
		gridPane.add(itemNamesListView, 0, 1);
		itemNamesListView.setCellFactory(list -> new ItemListCell());
		
		ListView<ItemWatch> itemsToWatchListView = new ListView<>(itemsToWatchList);
		gridPane.add(itemsToWatchListView, 2, 1);
		
		// Control Pane
		ImageView chaosImage = new ImageView(loadImage("/images/chaos.png"));
		Label currencyLabel = new Label("Amount", chaosImage);
		Spinner<Integer> amountSpinner = new Spinner<>(1, 10000, 5);
		amountSpinner.setPrefWidth(100);
		
		ComboBox<String> leaguesCmbx = new ComboBox<>(FXCollections.observableArrayList("Standard", "Hardcore", "Warbands", "Tempest"));
		leaguesCmbx.getSelectionModel().select(0);
		
		Button addButton = new Button();
		addButton.setText("Add");
		addButton.setPrefWidth(180);
		addButton.setOnAction(e -> {
			String itemName = itemNamesListView.getSelectionModel().getSelectedItem();
			if (itemName != null) {
				Integer amount = amountSpinner.getValue();
				String league = leaguesCmbx.getSelectionModel().getSelectedItem();
				Currency currency = new Currency(Currency.Type.chaos, amount);
				ItemWatch itemWatch = new ItemWatch(itemName, currency, league);
				
				itemNamesListView.getSelectionModel().clearSelection();
				itemsToWatchList.add(itemWatch);
			}
		});
		Button removeButton = new Button();
		removeButton.setText("Remove");
		removeButton.setPrefWidth(180);
		removeButton.setOnAction(e -> {
			ItemWatch itemWatch = itemsToWatchListView.getSelectionModel().getSelectedItem();
			if (itemWatch != null) {
				itemsToWatchList.remove(itemWatch);
			}
		});
		
		Button helpButton = new Button("?");
		helpButton.setOnAction(e -> openDurianHomePage());
		
		FlowPane controlsPane = new FlowPane(5, 1, leaguesCmbx, amountSpinner, currencyLabel, addButton, removeButton, helpButton);
		controlsPane.setAlignment(Pos.CENTER);
		centerPane.setCenter(gridPane);
		centerPane.setBottom(controlsPane);
		
		// Bottom Pane
		BorderPane bottomPanel = new BorderPane();
		consoleTextArea = new TextArea();
		consoleTextArea.setEditable(false);
		consoleTextArea.setPrefSize(scene.getWidth() - 10, scene.getHeight() / 3);
		bottomPanel.setCenter(consoleTextArea);
		
		root.setCenter(centerPane);
		root.setBottom(bottomPanel);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		writeToConsole("Sucessfully started " + title + " Time now: " + new Date());
		startWatchThread();
		
		primaryStage.setOnCloseRequest(e -> {
			timer.cancel();
		});
	}
	
	   private static class ItemListCell extends ListCell<String> {
	        @Override
	        public void updateItem(String item, boolean empty) {
	            super.updateItem(item, empty);
	            if (empty) {
	                setGraphic(null);
	                setText(null);
	            } else {
//	                Image fxImage = getFileIcon(item);
//	                ImageView imageView = new ImageView(fxImage);
//	                setGraphic(imageView);
	                setText(item);
	            }
	        }
	    }
	
	private void openDurianHomePage() {
		String homePage = "http://thirdy.github.io/durian";
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(homePage));
			} catch (IOException | URISyntaxException e) {
				writeToConsole("ERROR: " + e.getMessage());
			}
		} else {
			writeToConsole("Desktop.isDesktopSupported() is false. Please manually visit: " + homePage);
		}
	}

	private void writeToConsole(String line) {
		Platform.runLater(() -> consoleTextArea.appendText(line + System.getProperty("line.separator")));
	}
	
	private void startWatchThread() {
		writeToConsole("Checking for items now. And will check items every " + Main.DURATION.toMinutes() + " mins.");
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				writeToConsole("Now running Durian Item watch.. ");
				itemsToWatchList.stream().limit(10).forEach((i) -> {
					List<Item> results = BackendApi.searchUnique(i.getLeague(), i.getFullName(),
							i.getCurrency().getAmount());
					for (Item item : results) {
						writeToConsole(item.toWTB());
					}
				});
			}
		}, 0l, (long)Main.DURATION.toMillis());
	}

	private GridPane setupCenterGridPane() {
		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(5));
		gridpane.setHgap(10);
		gridpane.setVgap(10);
		ColumnConstraints column1 = new ColumnConstraints(130, 150, Double.MAX_VALUE);
		ColumnConstraints column2 = new ColumnConstraints(5);
		ColumnConstraints column3 = new ColumnConstraints(250, 150, Double.MAX_VALUE);
		column1.setHgrow(Priority.ALWAYS);
		column3.setHgrow(Priority.ALWAYS);
		gridpane.getColumnConstraints().addAll(column1, column2, column3);
		return gridpane;
	}

	private Image loadImage(String image) {
		try {
			return new Image(fromClasspathAsStream(image));
		} catch (IOException | URISyntaxException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new DurianUiException(e);
		}
	}

}
