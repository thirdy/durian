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
package net.thirdy.durian.util.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author thirdy
 *
 */
interface Config {
	default File load() throws IOException {
		File file = new File(configDirectory(), fileName());
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}
	
	default File configDirectory() {
		File file = new File(System.getProperty("user.home"), ".blackmarket");
		return file;
	}
	
	default List<String> loadLines() throws IOException {
		return Files.readAllLines(Paths.get(load().toURI()));
	}
	
	String fileName();
}
