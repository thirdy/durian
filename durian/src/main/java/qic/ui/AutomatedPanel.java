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

import static java.lang.String.format;
import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static qic.util.Config.AUTOMATED_SEARCH_BLACKLIST;
import static qic.util.Config.AUTO_VERIFY;
import static qic.util.Config.getBooleanProperty;
import static qic.util.DurianUtils.notBlacklisted;
import static qic.util.Util.sleep;
import static qic.util.Verify.SOLD;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qic.Command;
import qic.Main;
import qic.SearchPageScraper.SearchResultItem;
import qic.util.Config;
import qic.util.DurianUtils;
import qic.util.SoundUtils;
import qic.util.Util;
import qic.util.Verify;

/**
 * @author thirdy
 *
 */
public class AutomatedPanel extends JPanel {
	private static final String AUTOMATED_TXT_FILENAME = "automated.txt";

	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	JLabel statusLbl = new JLabel("Waiting for (0) seconds..");
	JButton runBtn = new JButton("Force");

	private Main main;
	private JTextArea searchListTa = new JTextArea(10, 15);
	private SearchResultTable table = new SearchResultTable();
	
	private static int countdown = 0;
	
	private ActionListener runCommand = e -> (new QueryTask(this)).execute();
	private javax.swing.Timer timer = new javax.swing.Timer(0, runCommand);
	
	private JSplitPane splitPane;
	
	public AutomatedPanel(Main main) {
		super(new BorderLayout(5, 5));
		this.main = main;
		
		table.setDoubleBuffered(true);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		controlPanel.add(runBtn);
		controlPanel.add(statusLbl);
		
		List<String> searchList = Util.loadSearchList(AUTOMATED_TXT_FILENAME);
		searchListTa.setText(searchList
				.stream()
				.collect(joining(lineSeparator())));

		runBtn.addActionListener(e -> {
			countdown = 0;
			timer.restart();
		});

		JPanel eastPanel = new JPanel(new BorderLayout(5, 5));
		eastPanel.add(controlPanel, BorderLayout.NORTH);
		eastPanel.add(new JScrollPane(searchListTa), BorderLayout.CENTER);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				new JScrollPane(table), eastPanel);
		
		this.add(splitPane, BorderLayout.CENTER);
		
		timer.setDelay(1000);
		logger.info("Starting timer...");
		timer.restart();
	}
	
	public void initSplitPaneDivider() {
		splitPane.setResizeWeight(.85d);
		splitPane.setDividerLocation(.85d);
	}

    private static class QueryTask extends SwingWorker<Void, SearchResultItem> {
    	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    	AutomatedPanel panel;
    	int waitMins = Integer.parseInt(Config.getPropety(Config.AUTOMATED_SEARCH_WAIT_MINUTES, "15"));
    	int waitSeconds = waitMins * 60;
    	
    	int waitSecondsInBetween = Integer.parseInt(Config.getPropety(Config.AUTOMATED_SEARCH_INBETWEEN_WAIT_SECONDS, "10"));
    	
        public QueryTask(AutomatedPanel panel) {
			this.panel = panel;
		}

		@Override
        protected Void doInBackground() {
			panel.statusLbl.setText("Waiting for (" + countdown + ") seconds..");
			if (countdown == 0) {
				countdown = waitSeconds;
				runJob();
			} else {
				countdown--;
			}
            return null;
        }

		private void runJob() {
			panel.runBtn.setEnabled(false);
			panel.table.clear();
			String text = panel.searchListTa.getText();
			if (!text.isEmpty()) {
				String[] searches = text.split("\n");
				logger.info("searches: " + Arrays.toString(searches));
				int total = 0;
	            int idx = 0;
	            while (!isCancelled() && idx < searches.length) {
	            	String prefix = Config.getPropety(Config.AUTOMATED_SEARCH_PREFIX, "tmpsc online bo").trim();
					String search = searches[idx].trim();
					String line = String.format("s %s %s", prefix, search);
	            	logger.info("Now running search: " + line);
					Command command = runQuery(line);
	                idx++;
	                List<SearchResultItem> itemResults = command.itemResults;
	                
	        		itemResults = itemResults.stream()
							.filter(item -> notBlacklisted(AUTOMATED_SEARCH_BLACKLIST, item))
							.collect(toList());
	        		
	    			if (getBooleanProperty(AUTO_VERIFY, false)) {
	    				int countAfterVerify = runVerify(itemResults);
	    				int difference = itemResults.size() - countAfterVerify;
						logger.info(format("Verified %d items, %d was confirmed verified. A difference of %d.", itemResults.size(), countAfterVerify, difference));
	    			} else {
	    				total += itemResults.size();
		    			itemResults.stream().forEach(this::publish);
	    			}
	    			
	    			if (idx < searches.length) {
	    				logger.info(format("Automated Search - now sleep for %d seconds", waitSecondsInBetween));
		    			sleep(waitSecondsInBetween * 1000);
					}
	            }
	            if (total > 0) {
	            	try {
						SoundUtils.tone(5000,100);
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
				}
			}
			panel.runBtn.setEnabled(true);
		}

		private int runVerify(List<SearchResultItem> itemResults) {
			return itemResults.stream()
				.mapToInt(item -> {
					int result = 0;
					
					logger.info(format("Verifying item %s", item.toShortDebugInfo()));
					Verify verified = DurianUtils.verify(item.thread(), item.dataHash());
					item.verified(verified);
					logger.info(format("Verify result for item %s: %s", item.toShortDebugInfo(), verified));
					long sleep = Config.getLongProperty(Config.AUTO_VERIFY_SLEEP, 100);
					
					if (verified != SOLD) {
						publish(item);
					}
					
					if (verified == Verify.VERIFIED) {
						result = 1;
					}
					
					logger.info(format("Auto-verify - now sleeping for %s millisec", sleep));
					sleep(sleep);
					
					return result;
				}).sum();
		}

        @Override
        protected void process(List<SearchResultItem> itemResults) {
				panel.table.addData(itemResults);
        }
        
    	private Command runQuery(String line) {
    		int count = 0;
    		int maxTries = 10;
    		while(true) {
    		    try {
    		    	return panel.main.processLine(line);
    		    } catch (Exception e) {
    		        if (++count == maxTries) throw new RuntimeException(e);
    		    }
    		}
    	}
    }
    
	public void saveToFile() {
		try {
			Util.overwriteFile(AUTOMATED_TXT_FILENAME, searchListTa.getText());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
