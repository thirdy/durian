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

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import qic.ui.extra.JTextAreaAppender;

/**
 * @author thirdy
 *
 */
public class LoggerPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	JTextArea textArea = new JTextArea();

	public LoggerPanel() {
		super(new BorderLayout());
		
		JTextAreaAppender appender = new JTextAreaAppender(textArea);
		String PATTERN = "%-4r [%t] %-5p %c %x - %m%n";
		appender.setLayout(new PatternLayout(PATTERN));
		appender.activateOptions();
		Logger.getRootLogger().addAppender(appender);

		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
}
