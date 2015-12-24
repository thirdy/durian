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

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author thirdy
 *
 */
public class JScrollPaneBG extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private Image image;
	
//	public JScrollPaneBG(Image image, Component component) {
	public JScrollPaneBG(Component component) {
		super(component);
		setOpaque(false);
        getViewport().setOpaque(false);
        try {
			this.image = ImageIO.read(new File("Path_of_Exile_Fan_Art_63.jpg"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        super.paintComponent(g);
    }

	public static void main(String[] args) throws IOException {
	    JFrame frame = new JFrame("Test");
	
	    final BufferedImage image = ImageIO.read(new File("Lenna.png"));
	
	    JTable table = new JTable(16, 3) {{
	        setOpaque(false);
	        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {{
	            setOpaque(false);
	        }});
	    }};
	
	    frame.add(new JScrollPane(table) {{
	            setOpaque(false);
	            getViewport().setOpaque(false);
	        }
	        @Override
	        protected void paintComponent(Graphics g) {
	            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	            super.paintComponent(g);
	        }
	
	    });
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);
	}
}
