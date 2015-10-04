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

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.impl.io.SocketOutputBuffer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import net.thirdy.durian.util.FileUtil;
import net.thirdy.durian.util.config.ItemWatchConfig.ItemWatch;

/**
 * @author thirdy
 *
 */
public class ItemWatchConfigTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testLoadNamesList() throws Exception {
		String defaultRawConfig = FileUtil.fromClasspath(ItemWatchConfig.class, "default-itemwatch.txt");
		List<String> list = Arrays.asList(defaultRawConfig.split(System.lineSeparator()));
		for (String string : list) {
			System.out.println(string);
		}
	}

}
