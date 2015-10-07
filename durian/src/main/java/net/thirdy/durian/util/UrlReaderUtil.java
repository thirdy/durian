package net.thirdy.durian.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class UrlReaderUtil {

	public static String getString(String url) throws IOException {
		InputStream in;
		String result = "";
		try {
			in = new URL( url ).openStream();
			try {
				result = IOUtils.toString( in );
			} finally {
				IOUtils.closeQuietly(in);
			}
		} catch (MalformedURLException e) {
			// shouldn't happen
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
	
	 
}
