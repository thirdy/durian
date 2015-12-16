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

import static java.util.Arrays.asList;
import static qic.util.Verify.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import qic.SearchPageScraper.SearchResultItem;

/**
 * @author thirdy
 *
 */
public class DurianUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(DurianUtils.class.getName());
	
	public static boolean notBlacklisted(String key, SearchResultItem item) {
		String blacklistStr = Config.getPropety(key, "");
		boolean isBlacklisted = asList(blacklistStr.split(","))
				.stream()
				.anyMatch(s -> s.equalsIgnoreCase(item.seller()));
		return !isBlacklisted;
	}

	public static Verify verify(String thread, String dataHash) {
		// http://verify.xyz.is/1504841/d571ac1d216e9e84dd1f44172c73abcb?callback=__callback_1450079321791_659&_=735.8083963058672
		
//		function query(thread, hash, callback) {
//			callback_name = "__callback_" + (new Date()).getTime() + "_" + Math.floor((Math.random() * 1000));
//			window[callback_name] = callback;
//			a = document.createElement('script');
//			var domain = "http://verify.xyz.is/";
//			a.src = domain + thread + "/" + hash + "?callback=" + callback_name + "&_=" + Math.random() * 1000;
//			a.type = "text/javascript";
//			document.getElementsByTagName("head")[0].appendChild(a);
//		}
		
		String url = String.format("http://verify.xyz.is/%s/%s", thread, dataHash);
		
		String callback_name = "__callback_" + (new Date()).getTime() + "_" + Math.floor((Math.random() * 1000));
		String underscoreValue = String.valueOf(Math.random() * 1000);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("callback", callback_name);
		parameters.put("_", underscoreValue);
		
		String verifyRaw = null;
		try {
			verifyRaw = getVerifyAndRetry(url, parameters, verifyRaw);
		} catch (UnirestException e) {
			return FAILED; // default to verified if call to verify failed
		}

//		__callback_1450079321791_659(true);
		String result = StringUtils.substringBetween(verifyRaw, callback_name + "(", ");");
		boolean verified = Boolean.parseBoolean(result);
		return verified ? VERIFIED : SOLD;
	}

	private static String getVerifyAndRetry(String url, Map<String, Object> parameters, String verifyRaw) throws UnirestException {
		int count = 0;
		int maxTries = 10;
		while(true) {
			try {
				return verifyRaw = Unirest.get(url)
						.header("Host","verify.xyz.is")
						.header("User-Agent","Mozilla/5.0 (Windows NT 6.1 WOW64 rv:41.0) Gecko/20100101 Firefox/41.0")
						.header("Accept","*/*")
						.header("Accept-Language","en-US,enq=0.5")
						.header("Connection","keep-alive")
						.header("Accept-Encoding","gzip, deflate")
						.queryString(parameters)
						.asString().getBody();
			} catch (UnirestException e) {
				if (++count == maxTries) {
					logger.error(String.format("Failed to verify using url %s, re-tried %d times.", url, maxTries), e);
					throw e;
				}
			}
		}
	}
}
