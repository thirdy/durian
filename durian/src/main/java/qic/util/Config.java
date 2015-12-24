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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author thirdy
 *
 */
public class Config {
	
	public static final String CONFIG_PROPERTIES_FILENAME = "config.properties";
	
	public static final String AUTOMATED_SEARCH_WAIT_MINUTES = "automated.search.wait.minutes";
	public static final String AUTOMATED_SEARCH_INBETWEEN_WAIT_SECONDS = "automated.search.inbetween.wait.seconds";
	public static final String MANUAL_SEARCH_PREFIX = "manual.search.prefix";
	public static final String AUTOMATED_SEARCH_PREFIX = "automated.search.prefix";
	public static final String AUTOMATED_SEARCH_SOUND_FILENAME = "automated.search.sound.filename";
	public static final String AUTOMATED_SEARCH_SOUND_VOLUME = "automated.search.sound.volume";
	public static final String AUTOMATED_SEARCH_ENABLED = "automated.search.enabled";
	
	public static final String AUTOMATED_SEARCH_BLACKLIST = "automated.search.blacklist";
	public static final String MANUAL_SEARCH_BLACKLIST = "manual.search.blacklist";
	
	public static final String AUTOMATED_AUTO_VERIFY = "automated.auto.verify";
	public static final String MANUAL_AUTO_VERIFY = "manual.auto.verify";
	
	public static final String AUTO_VERIFY_SLEEP = "auto.verify.sleep";


	
	private static Properties config;
	
	public static void loadConfig() throws IOException, FileNotFoundException {
		config = new Properties();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(CONFIG_PROPERTIES_FILENAME)))) {
			config.load(br);
		}
	}
    
    public static String getPropety(String key, String defaultValue) {
    	return config.getProperty(key, defaultValue).trim();
    }

	public static boolean getBooleanProperty(String key, boolean defaultValue) {
		String propety = getPropety(key, String.valueOf(defaultValue));
		return Boolean.parseBoolean(propety);
	}

	public static int getIntegerProperty(String key, int i) {
		String propety = getPropety(key, String.valueOf(i));
		return Integer.parseInt(propety);
	}

	public static long getLongProperty(String key, long i) {
		String propety = getPropety(key, String.valueOf(i));
		return Long.parseLong(propety);
	}
}
