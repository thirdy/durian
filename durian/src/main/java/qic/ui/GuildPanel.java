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

import static qic.util.Config.GUILD_LIST_FILENAME;
import static qic.util.SwingUtil.showError;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qic.ui.extra.Worker;
import qic.util.Config;
import qic.util.GuildPageScraper;
import qic.util.Util;

/**
 * @author thirdy
 *
 */
public class GuildPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	JTextArea textArea = new JTextArea();

	public GuildPanel() {
		super(new BorderLayout(1, 1));
		textArea.setFont(new Font("Consolas", Font.TRUETYPE_FONT, 12));
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		JPanel southPanel = new JPanel();
		JButton guildBtn = new JButton("Append with Guildmates");
		JButton saveBtn = new JButton("Save");
		JTextField guildUrl = new JTextField(50);
		// https://www.pathofexile.com/guild/profile/162231
		southPanel.add(new JLabel("Guild Profile No./Url"));
		southPanel.add(guildUrl);
		southPanel.add(guildBtn);
		southPanel.add(new JLabel("Discount: " + Config.getPropety(Config.GUILD_DISCOUNT_STRING, 
				Config.GUILD_DISCOUNT_STRING_DEFAULT)));
		add(southPanel, BorderLayout.SOUTH);
		southPanel.add(saveBtn);
		loadConfigToTextArea();
		
		guildBtn.addActionListener(e -> {
			String url = guildUrl.getText();
			if (!url.isEmpty()) {
				Worker<List<String>> worker = new Worker<List<String>>(() -> {
					List<String> members = Collections.emptyList();
					String urlFinal = StringUtils.isNumeric(url) ? 
							"https://www.pathofexile.com/guild/profile/" + url : url;
					try {
						members = GuildPageScraper.scrapeMembers(urlFinal);
					} catch (Exception ex) {
						logger.error("Error while scraping guild page: " + urlFinal);
						showError(ex);
					}
					return members;
				},
				guildNames -> {
					if (!guildNames.isEmpty()) {
						guildNames.stream().forEach(name -> {
							String ls = textArea.getText().isEmpty() ? "" : System.lineSeparator();
							textArea.append(ls + name);
						});	
					}
				});
				worker.execute();
			}
		});
		
		saveBtn.addActionListener(e -> save());
	}

	void save() {
		try {
			// directly calling getText() from TextArea is causing bad values, probably swing quirks
			Document document = textArea.getDocument();
			String content = document.getText(0, document.getLength());
			Util.overwriteFile(GUILD_LIST_FILENAME, content);
		} catch (BadLocationException | IOException e) {
			logger.error("Error while saving to " + GUILD_LIST_FILENAME);
			showError(e);
		}
	}

	private void loadConfigToTextArea() {
		try {
			String str = FileUtils.readFileToString(new File(GUILD_LIST_FILENAME));
			textArea.setText(str);
			textArea.setCaretPosition(0);
		} catch (IOException e) {
			logger.error("Error while reading " + GUILD_LIST_FILENAME);
			showError(e);
		}
	}

	public void tabSelected() {
		textArea.requestFocusInWindow();
	}
	
}
