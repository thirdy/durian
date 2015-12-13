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
package qic;

import java.util.Collections;
import java.util.List;

import qic.SearchPageScraper.SearchResultItem;
import qic.util.Util;

/**
 * @author thirdy
 *
 */
public class Command {

	public Command(String line) {
		this.input = line;
	}

	String input;
	Status status;
	public List<SearchResultItem> itemResults = Collections.emptyList();
	public String league;
	
	String errorMessage;
	String errorStackTrace;
	
	List<String> invalidSearchTerms;
	
	public Long searchDuration;
	
	public static enum Status {
		SUCCESS, ERROR, EXIT, INVALID
	}

	public String toJson() {
		return Util.toJsonPretty(this);
	}
}
