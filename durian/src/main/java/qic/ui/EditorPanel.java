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
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.apache.commons.io.FileUtils;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qic.ui.extra.FileTreeModel;
import qic.util.SwingUtil;
import qic.util.Util;

/**
 * @author thirdy
 *
 */
public class EditorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	private RSyntaxTextArea textArea;
	private JTree fileTree;
	private File currentScript;

	public EditorPanel() {
		setLayout(new BorderLayout());
		
		setupTextArea();
		setupFileTree();
		
		textArea.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				saveCurrentScriptToFile();
			}
			
			@Override
			public void focusGained(FocusEvent e) {}

		});
		
	}

	private void setupFileTree() {
		TreeModel model = new FileTreeModel(new File("."));
		fileTree = new JTree(model);
		fileTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		fileTree.addTreeSelectionListener(tse -> {
			File file = (File) fileTree.getLastSelectedPathComponent();
			 if (isFileEditable(file)) {
				 saveCurrentScriptToFile();
				 currentScript = file;
				 loadFileToTextArea(file);
			 }
		});
		fileTree.setSelectionRow(0);
		add(new JScrollPane(fileTree), BorderLayout.EAST);
	}

	private boolean isFileEditable(File file) {
		if (file != null && file.isFile()) {
			boolean isAudio = file.getName().endsWith(".wav") || file.getName().endsWith(".mp3");
			return !isAudio;
		}
		return false;
	}

	private void setupTextArea() {
		textArea = new RSyntaxTextArea(20, 60);
	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
	    textArea.setCodeFoldingEnabled(true);
		textArea.setFont(new Font("Consolas", Font.TRUETYPE_FONT, 12));
		add(new RTextScrollPane(textArea), BorderLayout.CENTER);
	}
	
	private void saveCurrentScriptToFile() {
		if (currentScript != null) {
			try {
				Util.overwriteFile(currentScript, textArea.getText());
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				SwingUtil.showError(e);
			}
		}
	}
	
	private void loadFileToTextArea(File file) {
		try {
			String str = FileUtils.readFileToString(file, "UTF-8");
			textArea.setText(str);
			textArea.setCaretPosition(0);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			SwingUtil.showError(e);
		}
	}
}
