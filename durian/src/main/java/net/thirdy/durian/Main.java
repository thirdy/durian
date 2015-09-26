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
package net.thirdy.durian;

import javafx.application.Application;
import javafx.util.Duration;
import net.thirdy.durian.backend.BackendApi;
import net.thirdy.durian.ui.DurianApplication;

/**
 *
 * @author thirdy
 */
public class Main {
	public static void main(String[] args) {
		BackendApi.setup();
		Application.launch(DurianApplication.class, args);
	}

	public static final Duration DURATION = Duration.minutes(15);
	
	// https://docs.google.com/spreadsheets/d/17evBWOCzVnbSmIvB-wvXRe5taE6Zyde04sol_jVtVnY/edit#gid=0
}
