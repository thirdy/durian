import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * After running, copy paste to google spreadsheets for viewing
 * 
 * @author thirdy
 *
 */
public class GetGithubDownloadStatistics {

	/**
	 * @param args
	 * @throws UnirestException
	 */
	public static void main(String[] args) throws UnirestException {
		System.out.println("download_count	created_at	owner	repo	tag_name	browser_download_url	html_url");
//		Arrays.asList("blackmarket", "poe.trade.assist", "durian", "wts").stream().forEach(s -> printRepo("thirdy", s));
		
//		printRepo("poeqic", "qic");
		printRepo("thirdy", "durian");
//		printRepo("bluemarlinexile", "bluemarlin");
		
//		printRepo("Stickymaddness", "Procurement");
//		printRepo("Novynn", "acquisitionplus");
//		printRepo("xyzz", "acquisition");
//		printRepo("EmmittJ", "PoESkillTree");
//		// printRepo("Bahnzo", "POE-ItemInfo"); unfortunately, the download is from google drive
//		
//		printRepo("survfate", "PoESimpleGuild");
//		printRepo("Gloorf", "poewatcher");
//		printRepo("Kapps", "PoEWhisperNotifier");
//		printRepo("M1nistry", "GuildStatus");
//		printRepo("ben-wallis", "Filtration");
		
		// printRepo("hbm50006", "tradewatch-master"); // no releases
		// http://bschug.github.io/poedit/poedit.html - awesome tool, I assume no releases
		// https://github.com/Asday/WhatAreTheChances no releases
		// https://github.com/OmegaK2/PyPoE no releases

	}

	private static void printRepo(String owner, String repo) {
		JSONArray array = null;
		try {
			array = Unirest.get("https://api.github.com/repos/" + owner + "/" + repo + "/releases")
//					.basicAuth("thirdy", "quicksandisunderratedbandandofcthisisnotmyrealpassword") // note that basic auth is optional but you'll be limited to 60 request per hour
					.asJson().getBody()
					.getArray();
			
			
			for (int i = 0; i < array.length(); i++) {

				JSONObject object = (JSONObject) array.get(i);
				String tag_name = object.getString("tag_name");
				String html_url = object.getString("html_url");
				
				JSONArray assets = (JSONArray) object.get("assets");

				for (int j = 0; j < assets.length(); j++) {
					JSONObject asset = (JSONObject) assets.get(j);
					String browser_download_url = asset.getString("browser_download_url");
					int download_count = asset.getInt("download_count");
					String created_at = asset.getString("created_at");

					print(created_at, owner, repo, html_url, tag_name, StringUtils.substringAfterLast(browser_download_url, "/"), download_count);
				}

			}
			
		} catch (UnirestException e) {
			e.printStackTrace();
		}


	}

	private static void print(String created_at, String owner, String repo, String html_url, String tag_name, String browser_download_url, int download_count) {
		System.out.println(String.format("%d\t%s\t%s\t%s\t%s\t%s\t%s", 
				download_count, created_at.subSequence(0, "2015-11-07".length()),owner,repo,tag_name,browser_download_url,html_url
				));
	}

}