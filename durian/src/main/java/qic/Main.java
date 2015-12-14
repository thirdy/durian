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

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static qic.Command.Status.ERROR;
import static qic.util.Config.AUTO_VERIFY;
import static qic.util.Config.getBooleanProperty;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qic.BlackmarketLanguage.ParseResult;
import qic.Command.Status;
import qic.SearchPageScraper.SearchResultItem;
import qic.ui.QicFrame;
import qic.util.CommandLine;
import qic.util.Config;
import qic.util.DurianUtils;
import qic.util.SessProp;
import qic.util.Util;

/**
 * TODO, REFACTOR!!!
 * 
 * @author thirdy
 *
 */
public class Main {
	
	private final static Logger logger = LoggerFactory.getLogger(Main.class.getName());
	
	public static BlackmarketLanguage language;
	BackendClient backendClient = new BackendClient();
	SessProp sessProp = new SessProp();
	Long searchDuration = null; 
	List<String> invalidSearchTerms = null; 
	
	public static void main(String[] args) throws Exception {
		try {
			reloadConfig();
			new Main(args);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error occured: " + e.getMessage());
			throw e;
		}
    }

	public static void reloadConfig() throws IOException, FileNotFoundException {
		language = new BlackmarketLanguage();
		Config.loadConfig();
	}

	public Main(String[] args) throws IOException, InterruptedException {
		CommandLine cmd = new CommandLine(args);
		boolean guiEnabled = cmd.hasFlag("-gui");
		guiEnabled = guiEnabled || cmd.getNumberOfArguments() == 0;

		logger.info("guiEnabled: " + guiEnabled);
		
		if (guiEnabled) {
			showGui(cmd.getArgument(0));
		} else {
			if (cmd.getNumberOfArguments() == 0) {
				throw new IllegalArgumentException("First arguement needed, and should be the query. e.g. 'search chest 100life 6s5L'. "
						+ "Enclosed in double quoutes if needed.");
			}
			String query = cmd.getArgument(0);
			logger.info("Query: " + query);
			
			Command command = processLine(query);
			String json = command.toJson();
			writeToFile(json);
		}
	}

	private void showGui(String query) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Windows".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // not worth my time
		    }
		}
		new QicFrame(this, query);
	}

	public void writeToFile(String contents) throws IOException {
		Util.overwriteFile("results.json", contents);
	}

	public Command processLine(String line) throws IOException {
		Command command = new Command(line);
		searchDuration = null;
		invalidSearchTerms = new LinkedList<>();
		try {
			if (line.startsWith("sort")&& !sessProp.getLocation().isEmpty()) {
				command.itemResults = runSearch(line, true);
				command.searchDuration = searchDuration;
			} else if (line.startsWith("s ")) {
				String terms = substringAfter(line, "s ").trim();
				if (!terms.isEmpty()) {
					command.itemResults = runSearch(terms, false);
					command.searchDuration = searchDuration;
				}
			}
			boolean autoVerify = getBooleanProperty(AUTO_VERIFY, false);
			if (autoVerify) {
				command.itemResults= command.itemResults.parallelStream()
					.filter(item -> {
						logger.info(String.format("Verifying item %s", item.toShortDebugInfo()));
						boolean verified = DurianUtils.verify(item.thread, item.dataHash);
						logger.info(String.format("Verify result for item %s: %s", item.toShortDebugInfo(), verified));
						return verified;
					})
					.collect(Collectors.toList());
			}
			
			command.league = sessProp.getLeague();
			command.invalidSearchTerms = invalidSearchTerms;
			command.status = Status.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			command.status = ERROR;
			command.errorMessage = e.getMessage();
			command.errorStackTrace = ExceptionUtils.getStackTrace(e);
		}
		return command;
	}

	private List<SearchResultItem> runSearch(String terms, boolean sortOnly) throws Exception {
		String html = downloadHtml(terms, sortOnly);
		if (html == null) {
			return Collections.emptyList();
		}
		SearchPageScraper scraper = new SearchPageScraper(html);
		List<SearchResultItem> items = scraper.parse();
		logger.info("items found: " + items.size());
		return items;
	}

	public String downloadHtml(String terms, boolean sortOnly) throws Exception {
		long start = System.currentTimeMillis();
		
		String regex = "([^\\s]*=\".*?\")";
		List<String> customHttpKeyVals = Util.regexMatches(regex, terms, 1);
		String customHttpKeyVal = customHttpKeyVals.stream()
				.map(s -> StringUtils.remove(s, '"'))
				.collect(Collectors.joining("&")); 
		String query = terms.replaceAll(regex, " ");
		
		ParseResult sortParseResult = language.parseSortToken(query);
		String sort = sortParseResult.result;
		sort = sort == null ? "price_in_chaos" : sort;
		invalidSearchTerms.addAll(sortParseResult.invalidSearchTerms);
		
		if (!sortOnly) {
			logger.info("Query: " + query);
			ParseResult queryParseResult = language.parse(query);
			String payload = queryParseResult.result;
			invalidSearchTerms.addAll(queryParseResult.invalidSearchTerms);
			if (invalidSearchTerms.isEmpty()) {
				payload = asList(payload, customHttpKeyVal, "capquality=x").stream().filter(StringUtils::isNotBlank).collect(joining("&"));
				logger.info("Unencoded payload: " + payload);
				payload = asList(payload.split("&")).stream().map(Util::encodeQueryParm).collect(joining("&"));
				String location  = submitSearchForm(payload);
				String league = language.parseLeagueToken(query);
				sessProp.setLocation(location);
				sessProp.setLeague(league);
				sessProp.saveToFile();
			}
		}

		String searchPage = null;
		if (invalidSearchTerms.isEmpty()) {
			logger.info("sort: " + sort);
			searchPage = ajaxSort(sort);
		} else {
			logger.info("invalidSearchTerms: " + invalidSearchTerms.toString());
		}
		
		long end = System.currentTimeMillis();
		long duration = end - start;
		logger.info("Took " + duration + " ms");
		searchDuration = Long.valueOf(duration);
		return searchPage;
	}

	private String ajaxSort(String sort) throws Exception {
		String searchPage = "";
		sort = URLEncoder.encode(sort, "UTF-8");
		sort = "sort=" + sort + "&bare=true";
		searchPage = backendClient.postXMLHttpRequest(sessProp.getLocation(), sort);
		return searchPage;
	}

	private String submitSearchForm(String payload) throws Exception {
		String url = "http://poe.trade/search";
		String location = backendClient.post(url, payload);
		return location;
	}


}
