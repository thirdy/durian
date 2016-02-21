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
package qic.ui.extra;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.porty.swing.table.model.BeanPropertyTableModel;

import qic.SearchPageScraper.SearchResultItem;
import qic.util.Config;

/**
 * @author thirdy
 *
 */
public class ArtColumnRenderer extends DefaultTableCellRenderer {
	
	private static final long serialVersionUID = 1L;
	
	private Color bgColor;
	private Color guildColor;
	private Color autoHighlightColor;
	private BeanPropertyTableModel<SearchResultItem> model;


	public ArtColumnRenderer(BeanPropertyTableModel<SearchResultItem> model, Color bgColor, Color guildColor, Color autoHighlightColor) {
		this.bgColor = bgColor;
		this.guildColor = guildColor;
		this.autoHighlightColor = autoHighlightColor;
		this.model = model;
		setHorizontalAlignment(JLabel.CENTER);
	}
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setText("");
        setIcon(null);
        
		if (isSelected) {
			setBackground(table.getSelectionBackground());
		} else {
			setBackground(bgColor != null ? bgColor : table.getBackground());
			if (!model.getData().isEmpty()) {
				SearchResultItem item = model.getData().get(row);
				if (item.newInAutomated()) {
					setBackground(autoHighlightColor);
				} else if (item.guildItem()) {
					setBackground(guildColor);
				}
			}
		}

        boolean artEnabled = Config.getBooleanProperty(Config.RESULT_TABLE_ART_ENABLED, true);
        if (artEnabled && value != null) {
//        	ImageIcon image = defaultImage;
//        	ImageIcon img = ImageCache.getInstance().get(value.toString());
//            if (img != null) {
//            	image = img;
//    		}
        	ImageIcon image = (ImageIcon) value;
            setIcon(image);
//            table.setRowHeight(row, image.getIconHeight());
		}
        
        return this;
    }

}
