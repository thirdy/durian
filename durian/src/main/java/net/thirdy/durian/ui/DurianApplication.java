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

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author thirdy
 *
 */
public class DurianApplication extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Durian v0.1");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 300, 250);
		
		Button btn = new Button();
		btn.setLayoutX(100);
		btn.setLayoutY(80);
		btn.setText("Hello World");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Hello World");
			}
		});
		root.setCenter(btn);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
