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
import static qic.util.Config.AUTOMATED_AUTO_VERIFY;
import static qic.util.Config.AUTOMATED_SEARCH_BLACKLIST;
import static qic.util.Config.getBooleanProperty;
import static qic.util.Config.getLongProperty;
import static qic.util.Config.getSoundMode;
import static qic.util.DurianUtils.notBlacklisted;
import static qic.util.Util.sleep;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

import com.porty.swing.table.model.BeanPropertyTableModel;

import qic.Command;
import qic.Main;
import qic.SearchPageScraper.SearchResultItem;
import qic.ui.extra.CaptchaDetectedException;
import qic.util.Config;
import qic.util.Config.SoundMode;
import qic.util.DurianUtils;
import qic.util.SoundUtilsFX;
import qic.util.SwingUtil;
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
		
		if (Config.getBooleanProperty(Config.AUTOMATED_SEARCH_ENABLED, false)) {
			timer.setDelay(1000);
			logger.info("Starting timer...");
			timer.restart();
		} else {
			runBtn.setEnabled(false);
		}
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
    	
    	int waitSecondsInBetween = Integer.parseInt(Config.getPropety(Config.AUTOMATED_SEARCH_INBETWEEN_WAIT_SECONDS, "15"));
    	
        public QueryTask(AutomatedPanel panel) {
			this.panel = panel;
		}

		@Override
        protected Void doInBackground() {
			panel.statusLbl.setText("Waiting for (" + countdown + ") seconds..");
			if (countdown == 0) {
				countdown = waitSeconds;
				try {
					runJob();
				} catch (Exception e) {
					SwingUtil.showError(e);
				} finally {
					panel.runBtn.setEnabled(true);
				}
			} else {
				countdown--;
			}
            return null;
        }

		private void runJob() throws CaptchaDetectedException {
			String text = panel.searchListTa.getText();
			if (!text.isEmpty()) {
				panel.runBtn.setEnabled(false);
				List<Integer> previousData = getTableModel().getData().stream().map(SearchResultItem::toUUID).collect(toList());
				panel.table.clear();
				String[] searches = text.split("\n");
				logger.info("searches: " + Arrays.toString(searches));
	            int idx = 0;
	            int total = 0;
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

	    			itemResults.stream().forEach(this::publish);
	        		
	    			int count = 0;
	    			if (getBooleanProperty(AUTOMATED_AUTO_VERIFY, false)) {
	    				long sleep = getLongProperty(Config.AUTOMATED_AUTO_VERIFY_SLEEP, 5000);
	    				count += runVerify(itemResults, sleep);
	    			} else {
						count += itemResults.size();
					}
	    			
	    			if (count > 0 && getBooleanProperty(Config.AUTOMATED_SEARCH_NOTIFY_NEWONLY, true)) {
	    				logger.info("Now checking if there are actually new items..");
	    				List<Integer> newItemsUUID = itemResults.stream().map(SearchResultItem::toUUID).collect(toList());
						boolean foundNew = newItemsUUID.stream().anyMatch(uuid -> !previousData.contains(uuid));
						logger.info("foundNew: " + foundNew);
	    				if (!foundNew) {
							count = 0;
						}
					}
	    			
	    			if (getSoundMode() == SoundMode.EACH_SEARCH && count > 0) {
	    				panel.playsound();
	    			}
	    			
	    			total += count;
	    			
	    			if (idx < searches.length) {
	    				logger.info(format("Automated Search - now sleep for %d seconds", waitSecondsInBetween));
		    			sleep(waitSecondsInBetween * 1000);
					}
	            }

    			if (Config.getSoundMode() == SoundMode.ONCE && total > 0) {
    				panel.playsound();
    			}
			}
		}

		@Override
        protected void process(List<SearchResultItem> itemResults) {
				panel.table.addData(itemResults);
        }
        
    	private Command runQuery(String line) throws CaptchaDetectedException {
    		int count = 0;
    		int maxTries = 10;
    		while(true) {
    		    try {
    		    	return panel.main.processLine(line);
    		    } catch (CaptchaDetectedException e) {
    		    	throw e;
    		    } catch (Exception e) {
    		        if (++count == maxTries) throw new RuntimeException(e);
    		    }
    		}
    	}
    	
    	private int runVerify(List<SearchResultItem> itemResults, long sleep) {
    		return itemResults.stream()
    			.mapToInt(item -> {
    				logger.info(format("Verifying item %s", item.toShortDebugInfo()));
    				Verify verified = DurianUtils.verify(item.thread(), item.dataHash());
    				item.verified(verified);
    				logger.info(format("Verify result for item %s: %s", item.toShortDebugInfo(), verified));
					BeanPropertyTableModel<SearchResultItem> model = getTableModel();
					int index = model.getData().indexOf(item);
					panel.table.updateData(index);
    				logger.info(format("Verify - now sleeping for %s millisec", sleep));
    				sleep(sleep);
    				int result = verified == Verify.VERIFIED ? 1 : 0;
    				return result;
    			}).sum();
    	}

    	@SuppressWarnings("unchecked")
		private BeanPropertyTableModel<SearchResultItem> getTableModel() {
			return (BeanPropertyTableModel<SearchResultItem>) panel.table.getModel();
		}
    }
    
	public void saveToFile() {
		try {
			Util.overwriteFile(AUTOMATED_TXT_FILENAME, searchListTa.getText());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void playsound() {
		String pathToFile = Config.getPropety(Config.AUTOMATED_SEARCH_SOUND_FILENAME, "notification.wav");
		double vol = Config.getDoubleProperty(Config.AUTOMATED_SEARCH_SOUND_VOLUME, 1.0);
		new Thread(() -> SoundUtilsFX.play(pathToFile, vol)).run();
	}

}
