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
package net.thirdy.durian.backend;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.thirdy.durian.model.Item;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;

/**
 *
 * @author thirdy
 */
public class BackendApiTest {

	public BackendApiTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		BackendApi.setup();
	}

	@AfterClass
	public static void tearDownClass() {
		BackendApi.shutdown();
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	@Ignore
	public void getAllMappings() throws Exception {
		String result = BackendApi.getAllMappings();
		System.out.println(result);
	}

	@Test
	public void searchUnique() throws Exception {
		List<Item> result = BackendApi.searchUnique("Tempest", "Mjolner", 360);
		result.stream().forEach(e -> System.out.println(e));
	}

	@Test
	public void searchUnique2() throws Exception {
		List<Item> result = BackendApi.searchUnique("Tempest", "Tabula Rasa", 15);
		result.stream().forEach(e -> System.out.println(e));
	}

	@Test
	public void searchUnique3() throws Exception {
		List<Item> result = BackendApi.searchUnique("Tempest", "The Bringer of Rain", 500);
		result.stream().forEach(e -> System.out.println(e));
	}
	
	@Test
	public void searchUnique4() throws Exception {
		List<Item> result = BackendApi.searchUnique("Tempest", "Wurm's Molt", 500);
		result.stream().forEach(e -> System.out.println(e));
	}
}
