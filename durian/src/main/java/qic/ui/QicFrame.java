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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qic.Main;
import qic.util.SoundUtilsFX;

/**
 * @author thirdy
 *
 */
public class QicFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	public QicFrame(Main main, String query) {
		super("Durian 0.6.0");
		
	    Image icon = Toolkit.getDefaultToolkit().getImage(QicFrame.class.getResource("/durian128.png"));
	    this.setIconImage(icon);
	    SoundUtilsFX.init();

		ManualPanel manualPanel = new ManualPanel(main);
		AutomatedPanel automatedPanel = new AutomatedPanel(main);
		LoggerPanel loggerPanel  = new LoggerPanel();
		ConfigPanel configPanel  = new ConfigPanel();
		TermsPanel termsPanel  = new TermsPanel();
		ScriptsPanel scriptsPanel  = new ScriptsPanel();
		GuildPanel guildPanel  = new GuildPanel();
		AboutPanel aboutPanel  = new AboutPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Manual", manualPanel);
		tabbedPane.addTab("Automated", automatedPanel);
		tabbedPane.addTab("Log", loggerPanel);
		tabbedPane.addTab("Config", configPanel);
		tabbedPane.addTab("Terms", termsPanel);
		tabbedPane.addTab("Scripts", scriptsPanel);
		tabbedPane.addTab("Guild", guildPanel);
		tabbedPane.addTab("About/Help", aboutPanel);
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				Component tab = tabbedPane.getSelectedComponent();
				if (tab instanceof ConfigPanel) {
					((ConfigPanel) tab).tabSelected();
				}
			}
		});
		
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
				logger.info("windowClosing: now saving file changes");
                manualPanel.saveToFile();
                automatedPanel.saveToFile();
				termsPanel.saveCurrentScriptToFile();
				scriptsPanel.saveCurrentScriptToFile();
				configPanel.saveAndReloadConfig();
            }
		});
		
		manualPanel.initSplitPaneDivider();
		automatedPanel.initSplitPaneDivider();
	}


}
