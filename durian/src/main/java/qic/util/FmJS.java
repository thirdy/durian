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

import static java.lang.String.format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qic.SearchPageScraper.SearchResultItem;

/**
 * @author thirdy
 *
 */
public class FmJS {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	private List<SearchResultItem> itemResults;

	public FmJS(List<SearchResultItem> itemResults) {
		this.itemResults = itemResults;
	}

	public void process() {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		String script = "script/fm.js";
		File scriptFile = new File(script);
		try {
			engine.eval(new FileReader(scriptFile));
			Invocable invocable = (Invocable) engine;
			Object result = invocable.invokeFunction("process", itemResults);
			logger.info(format("Script %s process result: %s", script, result));
		} catch (FileNotFoundException | ScriptException | NoSuchMethodException e) {
			logger.error(format("Error while running %s script", script), e);
		}
	}

}
