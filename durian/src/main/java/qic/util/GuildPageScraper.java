package qic.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuildPageScraper {
	
	private static final Logger logger = LoggerFactory.getLogger(GuildPageScraper.class.getName());

	public static List<String> scrapeMembers(String url) throws MalformedURLException, IOException {
		Document doc = Jsoup.parse(new URL(url), 10000);
		Element memberDiv = doc.getElementsByClass("members").get(0);
		List<Element> profileLinks = memberDiv
				.getElementsByAttributeValueContaining("href", "view-profile");
		logger.info("profileLinks.size() - " + profileLinks.size());
		List<String> names = profileLinks.stream()
			.map(aElem -> aElem.attr("href"))
			.map(href -> StringUtils.substringAfter(href, "view-profile/"))
			.collect(Collectors.toList());
		return names;
	}
}
