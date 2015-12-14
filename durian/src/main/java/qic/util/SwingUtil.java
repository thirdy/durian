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
package qic.util;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URI;

import javax.swing.JOptionPane;

/**
 * @author thirdy
 *
 */
public class SwingUtil {
	
	public static void openUrlViaBrowser(String url) {
		String s = url;
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(s));
			} catch (Exception e) {
				Dialogs.showError(new BlackmarketException(
						"Error on opening browser, address: " + s + ": " + e.getMessage(), e
							));
			}
		} else {
			Dialogs.showError(new BlackmarketException("Launch browser failed, please manually visit: " + s));
		}
	}
	
	public static void copyToClipboard(String s) {
		StringSelection stringSelection = new StringSelection(s);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
	}

	public static void showErrorMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public static void showError(Exception e) {
		new qic.util.SimpleExceptionHandler().uncaughtException(Thread.currentThread(), e);
	}
}
