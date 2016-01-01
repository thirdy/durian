package qic.util;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Util {
	
	private static final Logger logger = LoggerFactory.getLogger(Util.class.getName());
	
	public static String removeThoseDamnWhiteSpace(String s) {
		s = StringUtils.deleteWhitespace(s);
		StringBuilder sb = new StringBuilder();
		char[] charArray = s.toCharArray();
		for (char c : charArray) {
			if (!Character.isSpaceChar(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	 public static List<String> regexMatches(String regex, String s, int group) {
		 return regexMatches(regex, s, group, Pattern.CASE_INSENSITIVE);
	 }
	public static List<String> regexMatches(String regex, String s, int group, int flags) {
		 List<String> allMatches = new ArrayList<String>();
		 Matcher m = Pattern.compile(regex, flags)
		     .matcher(s);
		 while (m.find()) {
		   allMatches.add(m.group(group));
		 }
		 return allMatches;
	 }
	
	public static String encodeQueryParm(String queryParam) {
		String key = substringBefore(queryParam, "=");
		String value = substringAfter(queryParam, "=");
		try {
			value = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e); 
		}
		return key + "=" + value;
	}

	public static void overwriteFile(String fileName, String contents) throws IOException {
		File file = new File(fileName);
		overwriteFile(file, contents);
	}
	
	public static void overwriteFile(File file, String contents) throws IOException {
		FileUtils.writeStringToFile(file , contents, "UTF-8", false);
	}

	public static String toJsonPretty(Object obj) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(obj);
	}
	
	public static List<String> loadSearchList(String filename) {
		List<String> lines;
		try {
			lines = FileUtils.readLines(new File(filename));
			lines = lines.stream()
					.filter(s -> isNotBlank(s) && !s.startsWith(";"))
					.collect(toList());
				return lines;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
