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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qic.util.SwingUtil;

/**
 * @author thirdy
 *
 */
public class AboutPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	public AboutPanel() {
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		setLayout(boxLayout);
		
		JLabel aboutLbl = new JLabel("<html>"
				+ "<p>Durian Copyright (C) 2015 thirdy</p>"
				+ "<p>This program is free software: you can redistribute it and/or modify</p>"
				+ "<p>it under the terms of the GNU General Public License as published by</p>"
				+ "<p>the Free Software Foundation, either version 3 of the License, or</p>"
				+ "<p>(at your option) any later version.</p>"
				+ "<p>This program comes with ABSOLUTELY NO WARRANTY.</p>"
				+ "<p>A copy of the GNU General Public License can be found at https://github.com/thirdy/durian/blob/master/LICENSE</p>"
				+ "<br/>"
				+ "<br/>"
				+ "<p>Thank you for using Durian. Durian is a fan-made software and is not affiliated with Grinding Gear Games in any way.</p>"
				+ "<p>This software is 100% free and open source.</p>"
				+ "<p>Durian is a fan-made software and is not affiliated with Grinding Gear Games in any way.</p>"
				+ "<br/>"
				+ "<br/>"
				+ "<p>IGN: ManicCompression</p>"
				+ "<p>Reddit: /u/ProFalseIdol</p>"
				+ "</html>");
		
		String websiteUrl = "http://thirdy.github.io/durian/";
		JButton website = new JButton("Website: " + websiteUrl);
		website.addActionListener(e -> SwingUtil.openUrlViaBrowser(websiteUrl));
		
		String forumUrl = "https://www.pathofexile.com/forum/view-thread/1507190";
		JButton forum = new JButton("Forum Thread: " + forumUrl );
		forum.addActionListener(e -> SwingUtil.openUrlViaBrowser(forumUrl));
		
		String helpUrl = "http://thirdy.github.io/durian/help/help.htm";
		JButton help = new JButton("Search Term Helper: " + helpUrl );
		help.addActionListener(e -> SwingUtil.openUrlViaBrowser(helpUrl));

		add(Box.createRigidArea(new Dimension(5,10)));
		add(aboutLbl);
		add(Box.createRigidArea(new Dimension(5,10)));
		add(website);
		add(Box.createRigidArea(new Dimension(5,10)));
		add(forum);
		add(Box.createRigidArea(new Dimension(5,10)));
		add(help);
		
		JPanel helpPanel = new JPanel(new BorderLayout());
		helpPanel.setBorder(BorderFactory.createTitledBorder("Help"));
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		try {
			InputStream input = this.getClass().getResource("/help.txt").openStream();
			String str = IOUtils.toString(input);
			textArea.setText(str);
			input.close();
		} catch (Exception e) {
			logger.error("Error while reading help file", e);
		}
		helpPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(Box.createRigidArea(new Dimension(5,10)));
		add(helpPanel);
	}
}
