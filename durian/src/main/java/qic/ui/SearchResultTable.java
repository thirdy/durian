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

import static java.util.Arrays.asList;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;

import com.porty.swing.table.model.BeanPropertyTableModel;

import qic.SearchPageScraper.SearchResultItem;
import qic.ui.extra.MultiLineTableCellRenderer;
import qic.ui.extra.VerifierTask;
import qic.util.SwingUtil;

/**
 * @author thirdy
 *
 */
public class SearchResultTable extends JTable {
	
	private static final long serialVersionUID = 1L;
	private BeanPropertyTableModel<SearchResultItem> model;


	public SearchResultTable() {
		model = new BeanPropertyTableModel<>(SearchResultItem.class);
		model.setOrderedProperties(
				asList("id", "bo", "item", "seller", "reqs", "mods", "offense", "defense"));
		this.setModel(model);
		setColumnWidths(this.getColumnModel(), 
				asList( 1,    5,        220,    150,      50,    300,	  100,        100));
		
//		this.getSelectionModel().addListSelectionListener(e -> {
//			if(e.getValueIsAdjusting()) {
//				int selectedRow = this.getSelectedRow();
//				if (selectedRow > -1) {
//					SearchResultItem searchResultItem = model.getData().get(selectedRow);
//					SwingUtil.copyToClipboard(searchResultItem.wtb());
//				}
//			}
//		});
		
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
		        		new VerifierTask(Arrays.asList(searchResultItem), results -> {
		        			SearchResultTable.this.updateData(row, results.get(0));
		        		}).execute();
		        	}
				}
		    }
		});
		
		this.setDefaultRenderer(List.class, new MultiLineTableCellRenderer());
	}
	

	private void setColumnWidths(TableColumnModel columnModel, List<Integer> widths) {
		for (int i = 0; i < widths.size(); i++) {
			columnModel.getColumn(i).setMinWidth(widths.get(i));
		}
	}


	public void setData(List<SearchResultItem> itemResults) {
		model.setData(itemResults);
	}
	
	public void updateData(int index, SearchResultItem itemResult) {
		List<SearchResultItem> data = model.getData();
		data.remove(index);
		data.add(index, itemResult);
		setData(data);
	}


	public void addData(List<SearchResultItem> itemResults) {
		List<SearchResultItem> data = model.getData();
		data.addAll(itemResults);
		setData(data);
	}


	public void clear() {
		setData(new ArrayList<>());
	}
}
