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
package qic.ui;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static qic.util.Config.MANUAL_AUTO_VERIFY;
import static qic.util.Config.MANUAL_SEARCH_BLACKLIST;
import static qic.util.Config.getBooleanProperty;
import static qic.util.DurianUtils.notBlacklisted;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qic.Command;
import qic.Main;
import qic.SearchPageScraper.SearchResultItem;
import qic.ui.extra.Worker;
import qic.util.Config;
import qic.util.SwingUtil;
import qic.util.Util;

/**
 * @author thirdy
 *
 */
public class ManualPanel extends JPanel {
	private static final String MANUAL_TXT_FILENAME = "manual.txt";

	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	private JList<String> searchJList = new JList<>();
	private DefaultListModel<String> searchJListModel = new DefaultListModel<>();

	private JSplitPane splitPane;

	private SearchResultTable table = new SearchResultTable();

	@SuppressWarnings("serial")
	public ManualPanel(Main main) {
		super(new BorderLayout(5, 5));
		
		table.setDoubleBuffered(true);

		JTextField searchTf = new JTextField(100);
		JButton runBtn = new JButton("Run");
		runBtn.setPreferredSize(new Dimension(200, 10));
		JLabel invalidTermsLblLbl = new JLabel();
		invalidTermsLblLbl.setFont(invalidTermsLblLbl.getFont().deriveFont(Font.BOLD));
		JLabel invalidTermsLbl = new JLabel();
		invalidTermsLbl.setForeground(Color.RED);
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
		JLabel searchLbl = new JLabel(" Search: ");
		searchLbl.setFont(searchLbl.getFont().deriveFont(Font.BOLD));
		northPanel.add(searchLbl);
		northPanel.add(searchTf);
		northPanel.add(invalidTermsLblLbl);
		northPanel.add(invalidTermsLbl);
		northPanel.add(runBtn);
		this.add(northPanel, BorderLayout.NORTH);
		
		List<String> searchList = Util.loadSearchList(MANUAL_TXT_FILENAME);
		searchList.stream().forEach(searchJListModel::addElement);
		searchJList.setModel(searchJListModel);
		
		searchJList.addListSelectionListener(e -> {
			if (e.getValueIsAdjusting()) {
				searchTf.setText(trimToEmpty(searchJList.getSelectedValue()));
			}
		});
		searchJList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		        	int index = searchJList.locationToIndex(evt.getPoint());
		            if (index != -1) {
		            	String search = trimToEmpty(searchJListModel.getElementAt(index));
						searchTf.setText(search);
						runBtn.doClick();
					}
		        }
		    }
		});
		
		searchJList.getInputMap().put(KeyStroke.getKeyStroke("DELETE"),
                "doSomething");
		searchJList.getActionMap().put("doSomething", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = searchJList.getSelectedIndex();
 				if (selectedIndex != -1) {
 					searchJListModel.remove(selectedIndex);
 				}				
			}
		});
		
		ActionListener runCommand = e -> {
			String tfText = searchTf.getText().trim();
			if (!tfText.isEmpty()) {
				Worker<Command> worker = new Worker<Command>(
						() -> {
							runBtn.setEnabled(false);
							Command result = null;
							try {
								result = runQuery(main, tfText);
							} catch (Exception ex) {
								runBtn.setEnabled(true);
								SwingUtil.showError(ex);
							}
							return result;
						},
						command -> {
							if (command != null) {
								if (command.invalidSearchTerms.isEmpty()) {
									addDataToTable(command);
									saveSearchToList(tfText);
									invalidTermsLbl.setText("");
									invalidTermsLblLbl.setText("");
									if (getBooleanProperty(MANUAL_AUTO_VERIFY, false)) {
										long sleep = Config.getLongProperty(Config.MANUAL_AUTO_VERIFY_SLEEP, 5000);
										table.runAutoVerify(sleep);
									}
								} else {
									String invalidTermsStr = command.invalidSearchTerms.stream().collect(joining(", "));
									invalidTermsLbl.setText(invalidTermsStr + " ");
									invalidTermsLblLbl.setText(" Invalid: ");
								}
							}
							runBtn.setEnabled(true);
						});
				worker.execute();
			}
		};

		searchTf.addActionListener(runCommand);
		runBtn.addActionListener(runCommand);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				new JScrollPane(table), new JScrollPane(searchJList));
		
		this.add(splitPane, BorderLayout.CENTER);
	}

	private void addDataToTable(Command command) {
		List<SearchResultItem> itemResults = filterResults(command.itemResults);
		table.clear();
//		table.setData(itemResults);
		table.addData(itemResults);
		table.updateData(0);
	}

	private List<SearchResultItem> filterResults(List<SearchResultItem> itemResults) {
		itemResults = itemResults.stream()
				.filter(item -> notBlacklisted(MANUAL_SEARCH_BLACKLIST, item))
				.collect(toList());
		return itemResults;
	}
	
	public void initSplitPaneDivider() {
		splitPane.setResizeWeight(.85d);
		splitPane.setDividerLocation(.85d);
	}
	
	private void saveSearchToList(String tfText) {
		for (int i = 0; i < searchJListModel.size(); i++) {
			String s = searchJListModel.getElementAt(i);
			if (s.equalsIgnoreCase(tfText)) {
				return;
			}
		}
		searchJListModel.addElement(tfText);
	}

	private Command runQuery(Main main, String tfText) throws Exception {
		String line = null;
		if (StringUtils.startsWithIgnoreCase(tfText, "sort")) {
			line = tfText;
		} else {
			String prefix = Config.getPropety(Config.MANUAL_SEARCH_PREFIX, "tmpsc online bo").trim();
			line = String.format("s %s %s", prefix, tfText);
		}

		logger.info("Now running search: " + line);
		return main.processLine(line);
	}
	
	public void saveToFile() {
		StringBuilder contents = new StringBuilder("");
		for (int i = 0; i < searchJListModel.size(); i++) {
			String s = searchJListModel.getElementAt(i);
			contents.append(s);
			contents.append(lineSeparator());
		}
		try {
			Util.overwriteFile(MANUAL_TXT_FILENAME, contents.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
