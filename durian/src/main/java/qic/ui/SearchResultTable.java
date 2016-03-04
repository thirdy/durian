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
import static java.util.Arrays.asList;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.porty.swing.table.model.BeanPropertyTableModel;

import qic.SearchPageScraper.SearchResultItem;
import qic.ui.extra.ArtColumnRenderer;
import qic.ui.extra.MultiLineTableCellRenderer;
import qic.ui.extra.VerifierTask;
import qic.util.Config;
import qic.util.SwingUtil;

/**
 * @author thirdy
 *
 */
public class SearchResultTable extends JTable {
	
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	private BeanPropertyTableModel<SearchResultItem> model;
	private VerifierTask autoVerifierTask;

	public SearchResultTable() {
		model = new BeanPropertyTableModel<>(SearchResultItem.class);
		model.setOrderedProperties(
				asList("art", "bo", "item", "seller", "reqs", "mods", "offense", "defense"));
		this.setModel(model);
		setupColumnWidths();
		
		this.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (row > -1) {
		        	if (SwingUtilities.isLeftMouseButton(me)) {
		        		SearchResultItem searchResultItem = model.getData().get(row);
						SwingUtil.copyToClipboard(searchResultItem.wtb());
			        }
		        	if (SwingUtilities.isRightMouseButton(me)) {
		        		SearchResultItem searchResultItem  = model.getData().get(row);
		        		VerifierTask manualVerifierTask = new VerifierTask(asList(searchResultItem),
		        				results -> updateData(row),
		        				verified -> {},
		        				ex -> { logger.error("Error while running manual verify", ex); });
						manualVerifierTask.execute();
		        	}
				}
		    }
		});
		Color bgColor = Color.decode(Config.getPropety(Config.RESULT_TABLE_BG_COLOR, null));
		Color guildColor = Color.decode(Config.getPropety(Config.GUILD_COLOR_HIGHLIGHT, "#f6b67f"));
		Color corruptedColor = Color.decode(Config.getPropety(Config.CORRUPTED_COLOR_HIGHLIGHT, "#000000"));
		Color autoHighlightColor = Color.decode(
				Config.getPropety(Config.AUTOMATED_SEARCH_NOTIFY_NEWONLY_COLOR_HIGHLIGHT, "#ccff99"));
		this.setDefaultRenderer(List.class, new MultiLineTableCellRenderer(model, bgColor, guildColor, corruptedColor, autoHighlightColor));
		this.setDefaultRenderer(String.class, new MultiLineTableCellRenderer(model, bgColor, guildColor, corruptedColor, autoHighlightColor));
		this.setDefaultRenderer(ImageIcon.class, new ArtColumnRenderer(model, bgColor, guildColor, autoHighlightColor));
		
		model.addTableModelListener(e -> {
			boolean artEnabled = Config.getBooleanProperty(Config.RESULT_TABLE_ART_ENABLED, true);
			if (artEnabled) {
				for (int i = 0; i < model.getData().size(); i++) {
					SearchResultItem item = model.getData().get(i);
					ImageIcon art = item.getArt();
					if (art != null) {
						int height = art.getIconHeight();
						setRowHeight(i, height);
					}
				}
			}
		});
	}
	
	private void setupColumnWidths() {
		setColumnWidths(this.getColumnModel(), 
				asList( 115,    20,        205,    135,      50,    340,	  90,        90));
	}

	private void setColumnWidths(TableColumnModel columnModel, List<Integer> widths) {
		for (int i = 0; i < widths.size(); i++) {
			columnModel.getColumn(i).setMinWidth(widths.get(i));
		}
	}

	public void setData(List<SearchResultItem> itemResults) {
		List<SearchResultItem> data = model.getData();
		data.clear();
		data.addAll(itemResults);
		model.fireTableDataChanged();
		// https://community.oracle.com/thread/1486177?start=0&tstart=0
		setupColumnWidths();
	}

	public void updateData(int index) {
		model.fireTableRowsUpdated(index, index);
	}
	
	public void addData(List<SearchResultItem> itemResults) {
		List<SearchResultItem> data = model.getData();
		int sidx = data.size() - 1;
		data.addAll(itemResults);
		int eidx = data.size() - 1;
		model.fireTableRowsInserted(sidx, eidx);
		// https://community.oracle.com/thread/1486177?start=0&tstart=0
		setupColumnWidths();
	}

	public void clear() {
		model.getData().clear();
		model.fireTableDataChanged();
	}

	public void runAutoVerify(long sleep) {
		runAutoVerify(sleep, i -> {
			logger.info(format("Verified %d items.", i));
		}, ex -> {
			logger.error("Error while running Automated Verify", ex);
		});
	}
	
	public void runAutoVerify(long sleep, Consumer<Integer> onComplete, Consumer<Exception> onException) {
		Consumer<List<SearchResultItem>> consumer = itemList -> itemList.stream()
				.map(item -> model.getData().indexOf(item))
				.forEach(this::updateData);
		if(autoVerifierTask != null) autoVerifierTask.cancel(true);
		autoVerifierTask = new VerifierTask(model.getData(), consumer, 
				onComplete,
				onException,
				sleep, false);
		autoVerifierTask.execute();
	}

}
