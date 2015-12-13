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

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import qic.Main;

/**
 * @author thirdy
 *
 */
public class QicFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public QicFrame(Main main, String query) {
		super("Durian 0.3");

		ManualPanel manualPanel = new ManualPanel(main);
		AutomatedPanel automatedPanel = new AutomatedPanel(main);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Manual", manualPanel);
		tabbedPane.addTab("Automated", automatedPanel);

		setContentPane(tabbedPane);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width-50,screenSize.height-50);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
            public void windowClosing(WindowEvent e)
            {
                manualPanel.saveToFile();
                automatedPanel.saveToFile();
            }
		});
	}


}
