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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @author thirdy
 *
 */
public class SessProp extends Properties {
	
	private static final long serialVersionUID = 1L;

	public SessProp() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(getSessPropsFile()))) {
			load(br);
		}
	}
	
	public String getLocation() {
		return getProperty("location", "");
	}
	
	public void setLocation(String location) {
		setProperty("location", location);
	}

	private File getSessPropsFile() throws IOException {
		File file = new File("searchsession");
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	public void saveToFile() throws IOException {
		try (BufferedWriter out = new BufferedWriter(new FileWriter(getSessPropsFile()))) {
			store(out, "Saving session");
		}
	}

	public void setLeague(String league) {
		setProperty("league", league);
	}
	
	public String getLeague() {
		return getProperty("league", "");
	}

}
