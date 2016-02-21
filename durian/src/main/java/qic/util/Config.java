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

import org.apache.commons.lang3.StringUtils;

/**
 * @author thirdy
 *
 */
public class Config {
	
	public static final String CONFIG_PROPERTIES_FILENAME = "config.properties";
	public static final String GUILD_LIST_FILENAME = "guild.txt";
	
	public static final String AUTOMATED_SEARCH_WAIT_MINUTES = "automated.search.wait.minutes";
	public static final String AUTOMATED_SEARCH_INBETWEEN_WAIT_SECONDS = "automated.search.inbetween.wait.seconds";
	public static final String MANUAL_SEARCH_PREFIX = "manual.search.prefix";
	public static final String AUTOMATED_SEARCH_PREFIX = "automated.search.prefix";
	public static final String AUTOMATED_SEARCH_SOUND_FILENAME = "automated.search.sound.filename";
	public static final String AUTOMATED_SEARCH_SOUND_VOLUME = "automated.search.sound.volume";
	public static final String AUTOMATED_SEARCH_SOUND_MODE = "automated.search.sound.mode";
	public static final String AUTOMATED_SEARCH_ENABLED = "automated.search.enabled";
	public static final String AUTOMATED_SEARCH_NOTIFY_NEWONLY = "automated.search.notify.newonly";
	public static final String AUTOMATED_SEARCH_NOTIFY_NEWONLY_COLOR_HIGHLIGHT = "automated.search.notify.newonly.color.highlight";
	public static final String RESULT_TABLE_ART_ENABLED = "result.table.art.enabled";
	public static final String RESULT_TABLE_BG_COLOR = "result.table.bg.color";
	
	public static final String GUILD_DISCOUNT_STRING = "guild.discount.string";
	public static final String GUILD_COLOR_HIGHLIGHT = "guild.color.highlight";
	public static final String GUILD_DISCOUNT_STRING_DEFAULT = "1ch";
	
	public static final String AUTOMATED_SEARCH_BLACKLIST = "automated.search.blacklist";
	public static final String MANUAL_SEARCH_BLACKLIST = "manual.search.blacklist";
	
	public static final String AUTOMATED_AUTO_VERIFY = "automated.auto.verify";
	public static final String MANUAL_AUTO_VERIFY = "manual.auto.verify";
	
	public static final String AUTOMATED_AUTO_VERIFY_SLEEP = "automated.auto.verify.wait.millisec";
	public static final String MANUAL_AUTO_VERIFY_SLEEP = "manual.auto.verify.wait.millisec";

	public static final String LOOK_AND_FEEL = "lookandfeel";
	public static final String LOOK_AND_FEEL_DECORATED = "lookandfeel.decorated.enabled";

	private static Properties config;
	
	public static void loadConfig() throws IOException, FileNotFoundException {
		config = new Properties();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(CONFIG_PROPERTIES_FILENAME)))) {
			config.load(br);
		}
	}
    
    public static String getPropety(String key, String defaultValue) {
    	return StringUtils.defaultIfBlank(config.getProperty(key, defaultValue), defaultValue);
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

	public static double getDoubleProperty(String key, double i) {
		String propety = getPropety(key, String.valueOf(i));
		return Double.parseDouble(propety);
	}
	
	public static SoundMode getSoundMode() {
		String propety = getPropety(AUTOMATED_SEARCH_SOUND_MODE, SoundMode.EACH_SEARCH.name());
		return SoundMode.valueOf(propety);
	}
	
	public static enum SoundMode {
		EACH_SEARCH, ONCE
	}

	public static Properties getProperties() {
		return config;
	}
}
