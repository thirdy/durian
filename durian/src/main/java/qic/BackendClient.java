package qic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.lang3.RandomUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BackendClient {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private HttpClient client = HttpClientBuilder.create().build();
    
    int timeout = 30;
	int CONNECTION_TIMEOUT = timeout  * 1000; // timeout in millis
    RequestConfig requestConfig = RequestConfig.custom()
        .setConnectionRequestTimeout(CONNECTION_TIMEOUT)
        .setConnectTimeout(CONNECTION_TIMEOUT)
        .setSocketTimeout(CONNECTION_TIMEOUT)
        .build();
    
    public final String userAgent;

    public BackendClient() {
    	userAgent = userAgents[RandomUtils.nextInt(0, userAgents.length)];
	}
    public String post(String payload)
            throws Exception {
    	return post("http://poe.trade/search", payload);
    }
    
    public String post(String url, String payload)
    			throws Exception {
    	logger.info("post() payload: " + payload);
    	logger.info("post() url: " + url);
    	
        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);

        // add header
        post.setHeader("Host", "poe.trade");
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        post.setHeader("Accept-Language", "en-US,en;q=0.5");
        post.setHeader("Accept-Encoding", "gzip, deflate");
        post.setHeader("Referer", "http://poe.trade/");
    	post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        post.setHeader("Connection", "keep-alive");

        post.setEntity(new StringEntity(payload));

        logger.info("Sending 'POST' request to URL : " + url);
        // bombs away!
        HttpResponse response = client.execute(post);

        int responseCode = response.getStatusLine().getStatusCode();

        logger.info("Response Code : " + responseCode);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        
        rd.close();
        
        String location = null;
        
        final Header[] allHeaders = response.getAllHeaders();
        for (Header header : allHeaders) {
            if (header.getName().equalsIgnoreCase("Location")) {
                location = header.getValue();
            }
        }
        
        return location;
    }
    
    public String postXMLHttpRequest(String url, String payload)
    		throws Exception {
    	logger.info("postXMLHttpRequest() payload: " + payload);
    	logger.info("postXMLHttpRequest() url: " + url);
    	StringEntity entity = new StringEntity(payload);
    	
    	HttpPost post = new HttpPost(url);
    	post.setConfig(requestConfig);
    	
    	// add header
    	post.setHeader("Accept", "*/*");
    	post.setHeader("Accept-Encoding", "gzip, deflate");
    	post.setHeader("Accept-Language", "en-US,en;q=0.5");
    	post.setHeader("Cache-Control", "no-cache");
    	post.setHeader("Connection", "keep-alive");
    	post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    	post.setHeader("Host", "poe.trade");
    	post.setHeader("Pragma", "no-cache");
    	post.setHeader("Referer", url);
    	post.setHeader("User-Agent", USER_AGENT);
    	post.setHeader("X-Requested-With", "XMLHttpRequest");
    	
		post.setEntity(entity);
    	
    	logger.info("Sending 'POST' request to URL : " + url);
    	// bombs away!
    	HttpResponse response = client.execute(post);
    	
    	int responseCode = response.getStatusLine().getStatusCode();
    	
    	logger.info("Response Code : " + responseCode);
    	
    	BufferedReader rd = new BufferedReader(
    			new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
    	
    	StringBuilder result = new StringBuilder();
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    		result.append(line);
    	}
    	
    	rd.close();
    	
//    	String location = null;
    	
//    	final Header[] allHeaders = response.getAllHeaders();
//    	for (Header header : allHeaders) {
//    		if (header.getName().equalsIgnoreCase("Location")) {
//    			location = header.getValue();
//    		}
//    	}
    	
    	return result.toString();
    }
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0";

    public String get(String url) throws Exception {

        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);
        
        get.setHeader("Host", "poe.trade");
        get.setHeader("User-Agent", USER_AGENT);
        get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        get.setHeader("Accept-Language", "en-US,en;q=0.5");
        get.setHeader("Accept-Encoding", "gzip, deflate");
        get.setHeader("Referer", "http://poe.trade/");
        get.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        
        HttpResponse response = client.execute(get);
        int responseCode = response.getStatusLine().getStatusCode();

        logger.info("Sending 'GET' request to URL : " + url);
        logger.info("Response Code : " + responseCode);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        
        rd.close();

        return result.toString();
    }

    public static final String userAgents[] = new String[] {
    		"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13",
    	    "Mozilla/5.0 (Windows; U; Windows NT 6.0; de) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13",
    	    "Mozilla/5.0 (Windows; U; Windows NT 5.2; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13",
    	    "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13(KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13",
    	    "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13",
    	    "Mozilla/5.0 (Windows; U; Windows NT 5.0; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13",
    	    "Mozilla/5.0 (Linux; U; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13",
    		
    		"Mozilla/5.0 (Macintosh; U; Mac OS X 10_6_1; en-US) AppleWebKit/530.5 (KHTML, like Gecko) Chrome/ Safari/530.5",
    	    "Mozilla/5.0 (Macintosh; U; Mac OS X 10_5_7; en-US) AppleWebKit/530.5 (KHTML, like Gecko) Chrome/ Safari/530.5",
    	    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_6; en-US) AppleWebKit/530.9 (KHTML, like Gecko) Chrome/ Safari/530.9",
    	    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_6; en-US) AppleWebKit/530.6 (KHTML, like Gecko) Chrome/ Safari/530.6",
    	    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_6; en-US) AppleWebKit/530.5 (KHTML, like Gecko) Chrome/ Safari/530.5",
    		
    	    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1",
    	    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0",
    	    "Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.0",
    	    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10; rv:33.0) Gecko/20100101 Firefox/33.0",
    	    "Mozilla/5.0 (X11; Linux i586; rv:31.0) Gecko/20100101 Firefox/31.0",
    	    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:31.0) Gecko/20130401 Firefox/31.0",
    	    "Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0",
    	    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20120101 Firefox/29.0",
    	    "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/29.0"
    };

}