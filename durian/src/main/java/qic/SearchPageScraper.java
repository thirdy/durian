package qic;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.time.LocalDate.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;
import static org.apache.commons.lang3.StringUtils.substringBetween;
import static org.apache.commons.lang3.StringUtils.trim;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static org.apache.commons.lang3.StringUtils.trimToNull;
import static org.apache.commons.lang3.StringUtils.upperCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import qic.SearchPageScraper.SearchResultItem.Mod;
import qic.util.Util;

/**
 *
 * @author thirdy
 */
public class SearchPageScraper {

	private static final String regex_horizontal_whitespace = "(^\\h*)|(\\h*$)";
	private String page;

	public SearchPageScraper(String page) {
		this.page = page;
//		try {
//			Util.overwriteFile("results.htm", page);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public List<SearchResultItem> parse() {
		List<SearchResultItem> searchResultItems = new LinkedList<>();
		Document doc = Jsoup.parse(page, "UTF-8");
		
		Element content = doc.getElementById("content");

		Elements items = null;
		if (content == null) {
			items = doc.getElementsByClass("item");
		} else {
			items = content.getElementsByClass("item");
		}

//		System.out.println(items.get(86).toString());

//		System.out.println("Items");
		for (Element element : items) {
			
			SearchResultItem item = new SearchResultItem();

			item.id = element.attr("id");
			item.id = StringUtils.remove(item.id, "item-container-");
			item.seller = element.attr("data-seller");
			item.thread = element.attr("data-thread");
			item.sellerid = element.attr("data-sellerid");
			item.buyout = element.attr("data-buyout");
			item.ign = element.attr("data-ign");
			item.league = element.attr("data-league");
			item.name = element.attr("data-name");
			item.corrupted = element.getElementsByClass("corrupted").size() > 0;
			item.identified = element.getElementsByClass("item-unid").size() == 0;
			

//			System.out.println(String.format("Now parsing item id %s name %s", item.id, item.name));
			
			Element sockElem = element.getElementsByClass("sockets-raw").get(0);
			item.socketsRaw = sockElem.text();
			
			Elements accntAgeElement = element.getElementsByAttributeValue("title", "account age and highest level");
			if (accntAgeElement != null && !accntAgeElement.isEmpty()) {
				item.ageAndHighLvl = accntAgeElement.get(0).text();
			}
			
			// ----- Requirements ----- //
			Element reqElem = element.getElementsByClass("requirements").get(0);
			List<TextNode> reqNodes = reqElem.textNodes();
			for (TextNode reqNode : reqNodes) {
				// sample [ Level:&nbsp;37 ,  Strength:&nbsp;42 ,  Intelligence:&nbsp;42 ] 
				String req = StringUtils.trimToEmpty(reqNode.getWholeText());
				req = req.replaceAll(regex_horizontal_whitespace,"");
				req = Util.removeThoseDamnWhiteSpace(req);
				String separator = ":";
				String reqType = trim(substringBefore(req, separator));
				switch(reqType) {
				case "Level":
					item.reqLvl = trim(substringAfter(req, separator));
					break;
				case "Strength":
					item.reqStr = trim(substringAfter(req, separator)); 
					break;
				case "Intelligence":
					item.reqInt = trim(substringAfter(req, separator)); 
					break;
				case "Dexterity":
					item.reqDex = trim(substringAfter(req, separator)); 
					break;
				}
			}
			item.mapQuantity = element.getElementsByAttributeValue("data-name", "mapq").stream()
					.findFirst()
					.map(n -> n.text())
					.map(s -> substringAfter(s, "Item quantity:"))
					.map(s -> StringUtils.removePattern(s, "[^\\d]"))
					.orElse("")
					.replaceAll(regex_horizontal_whitespace,"").trim();

			// ----- Mods ----- //
			Elements itemModsElements = element.getElementsByClass("item-mods");
			if (itemModsElements != null && itemModsElements.size() > 0) {
				Element itemMods = itemModsElements.get(0);
				if (itemMods.getElementsByClass("bullet-item").size() != 0) {
					Element bulletItem = itemMods.getElementsByClass("bullet-item").get(0);
					Elements ulMods = bulletItem.getElementsByTag("ul");
					if (ulMods.size() == 2) {
						// implicit mod
						Elements implicitLIs = ulMods.get(0).getElementsByTag("li");
						Element implicitLi = implicitLIs.last();
						Mod impMod = new Mod(implicitLi.attr("data-name"), implicitLi.attr("data-value"));
						item.implicitMod = impMod;
					}
					int indexOfExplicitMods = ulMods.size() - 1;
					Elements modsLi = ulMods.get(indexOfExplicitMods).getElementsByTag("li");
					for (Element modLi : modsLi) {
						// explicit mods
						Mod mod = new Mod(modLi.attr("data-name"), modLi.attr("data-value"));
						item.explicitMods.add(mod);
					}
				}
			}
			
			// ----- Properties ----- //
			// this is the third column data (the first col is the image, second is the mods, reqs)
			item.quality = element.getElementsByAttributeValue("data-name", "q").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.physDmgRangeAtMaxQuality = element.getElementsByAttributeValue("data-name", "pd").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.eleDmgRange = element.getElementsByAttributeValue("data-name", "ed").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.attackSpeed = element.getElementsByAttributeValue("data-name", "aps").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.dmgAtMaxQuality = element.getElementsByAttributeValue("data-name", "dps").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.physDmgAtMaxQuality = element.getElementsByAttributeValue("data-name", "pdps").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.eleDmg = element.getElementsByAttributeValue("data-name", "edps").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.armourAtMaxQuality = element.getElementsByAttributeValue("data-name", "armour").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.evasionAtMaxQuality = element.getElementsByAttributeValue("data-name", "evasion").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.energyShieldAtMaxQuality = element.getElementsByAttributeValue("data-name", "shield").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.block = element.getElementsByAttributeValue("data-name", "block").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.crit = element.getElementsByAttributeValue("data-name", "crit").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.level = element.getElementsByAttributeValue("data-name", "level").get(0).text().replaceAll(regex_horizontal_whitespace,"").trim();
			item.imageUrl = element.getElementsByAttributeValue("alt", "Item icon").get(0).attr("src");
			item.stackSize = asList(split(trimToEmpty(item.imageUrl), '&')).stream()
					.filter(t -> t.startsWith("stackSize="))
					.findFirst().map(s -> substringAfter(s, "=")).orElse(null);
			
			Elements onlineSpans = element.getElementsMatchingText("online");
			if (!onlineSpans.isEmpty()) {
				item.online="Online";
			} else {
				item.online="";
			}
			
			searchResultItems.add(item);
		}
//		System.out.println("DONE --- Items");

		return searchResultItems;
	}

	/**
	 * Models one item in the search results
	 */
	public static class SearchResultItem {

		String id; // the id in the search result html page
		String buyout;
		String name;
		String ign;
		public boolean corrupted;
		public boolean identified;
		
		String socketsRaw;
		String stackSize;
		
		String quality;
		
		String physDmgRangeAtMaxQuality;
		String physDmgAtMaxQuality;
		String eleDmgRange;
		String attackSpeed;
		String dmgAtMaxQuality;
		String crit;
		String level;
		String eleDmg;
		
		String armourAtMaxQuality;
		String evasionAtMaxQuality;
		String energyShieldAtMaxQuality;
		String block;
		
		String reqLvl;
		String reqStr;
		String reqInt;
		String reqDex;
		String mapQuantity;

		String ageAndHighLvl;
		String league;
		String seller;
		String thread;
		String sellerid;
		String threadUrl;
		String online;
		
		String imageUrl;

		Mod implicitMod;
		List<Mod> explicitMods = new ArrayList<>();
		
		
		public List<Mod> getMods() {
			List<Mod> mods = explicitMods.stream().collect(Collectors.toList());
			if (implicitMod != null) {
				mods.add(0, new Mod("--------------", null));
				mods.add(0, implicitMod);
			}
			return mods;
		}
		
		public List<String> getReqs() {
			return labelList(
					labelVal("Lvl", reqLvl), 
					labelVal("Str", reqStr),
					labelVal("Dex", reqDex),
					labelVal("Int", reqInt));
		}
		
		public List<String> getItem() {
			return labelList(
					labelVal("Name", name), 
					labelVal("Identified", String.valueOf(identified)), 
					labelVal("Corrupted", String.valueOf(corrupted)), 
					labelVal("SocketsRaw", socketsRaw),
					labelVal("StackSize", stackSize));
		}
		
		private List<String> labelList(String ... labels) {
			return asList(labels).stream()
					.filter(Objects::nonNull)
					.collect(toList());
		}

		public List<String> getSeller() {
			String highestLvl = substringAfter(ageAndHighLvl, "h");
			String age = substringBetween(ageAndHighLvl, "a", "h");
			age = StringUtils.isNumeric(age) ? now().minusDays(parseInt(age)).format(ofPattern("MMM dd uuuu")) : age;
			return labelList(
					labelVal("Joined ", age),
					labelVal("HighestLvl ", highestLvl),
					labelVal("IGN", ign),
					labelVal("Thread", thread),
//					labelVal("", sellerid),
//					labelVal("", threadUrl),
					labelVal("Online", String.valueOf(containsIgnoreCase("online", online))));
		}
		
		private String labelVal(String label, String val) {
			return trimToNull(val) == null ? null : label + ": " + val;
		}

		public String wtb() {
//			String mods = buildWTBModsMessage();
//			return String.format(
//					"@%s Hi, I would like to buy your %s listed for %s in %s. With stats%s",
//					getIgn(), getName(), getBuyout(), getLeague(), mods);
			return String.format(
					"@%s Hi, WTB your \"%s\" listed for %s in %s league.",
					ign, name, upperCase(getBuyout()), league);
		}

//		private String buildWTBModsMessage() {
//			StringBuilder sb = new StringBuilder();
//			if (implicitMod != null) {
//				sb.append("--- [Implicit] " + implicitMod.toStringDisplay());
//			}
//			if (explicitMods.size() > 0) {
//				sb.append("--- [Explicit]");
//				for (Mod mod : explicitMods) {
//					sb.append(" --- " + mod.toStringDisplay());
//				}
//			}
//			return sb.toString();
//		}

		/**
		 * @author thirdy
		 *
		 */
		public static class Mod {
			String name;
			String value;

			public Mod(String name, String value) {
				this.name = name;
				this.value = value;
			}
			
			public String getName() {
				return name;
			}

			public String getValue() {
				return value;
			}

			@Override
			public String toString() {
//				return System.lineSeparator() + "Mod [name=" + name + ", value=" + value + "]";
				return toStringDisplay();
			}

			/*  Convert the ff into human readable form:
			    #Socketed Gems are Supported by level # Increased Area of Effect
				##% increased Physical Damage
				#+# to Strength
				#+# to Accuracy Rating
				#+# Mana Gained on Kill
				#+# to Weapon range
			 */
			public String toStringDisplay() {
				String display = name;
				if (StringUtils.startsWith(name, "#") || StringUtils.startsWith(name, "$")) {
					display = StringUtils.removeStart(display, "#");
					display = StringUtils.removeStart(display, "$");
					String val = StringUtils.endsWith(value, ".0") ? StringUtils.substringBefore(value, ".0") : value;
					display = StringUtils.replaceOnce(display, "#", val);
				}
				return display;
			}
		}
		
		public String toDisplay(String newLine) {
			StringBuilder builder = new StringBuilder();
			String _quality  = isNotBlank(quality) ? " " + quality + "%" : "";
			String linksocks = isNotBlank(socketsRaw) ? " " + socketsRaw : "";
			String strCorrupt = corrupted ? " Corrupted " : "";
			strCorrupt += !identified ? " Unidentified " : "";
			builder.append(format("[%s] %s%s%s", id, name, linksocks, _quality));
			builder.append(format("%s -----%s------ ", newLine, strCorrupt));
			builder.append(newLine);
			if (implicitMod != null) {
				builder.append(format("%s", implicitMod.toStringDisplay()));
				builder.append(newLine + " ----------- " + newLine);
			}
			if (explicitMods.size() > 0) {
				for (Mod mod : explicitMods) {
					builder.append(format("%s", mod.toStringDisplay()));
					builder.append(newLine);
				}
				builder.append("-----------" + newLine);
			}
			String _physDmg 	= isNotBlank(physDmgAtMaxQuality) ? ("pDPS " + physDmgAtMaxQuality) : "";
			String _eleDmg 		= isNotBlank(eleDmg) 			  ? ("eDPS " + eleDmg) : "";
			String _attackSpeed = isNotBlank(attackSpeed) 		  ? ("APS " + attackSpeed) : "";
			String _crit 		= isNotBlank(crit) 				  ? ("Cc " + crit) : "";
			String offense = format("%s %s %s %s", _physDmg, _eleDmg, _attackSpeed, _crit).trim();
			offense = offense.isEmpty() ? "" : (offense + newLine);
			builder.append(offense);
			
			String _armour 		= isNotBlank(armourAtMaxQuality) 	? ("Ar " + armourAtMaxQuality) : "";
			String _evasion 	= isNotBlank(evasionAtMaxQuality) 	? ("Ev " + evasionAtMaxQuality) : "";
			String _energyShield = isNotBlank(energyShieldAtMaxQuality) ? ("Es " + energyShieldAtMaxQuality) : "";
			String _block 		= isNotBlank(block) 				? ("Bk " + block) : "";
			String defense = format("%s %s %s %s", _armour, _evasion, _energyShield, _block).trim();
			defense = defense.isEmpty() ? "" : (defense + newLine);
			builder.append(defense);
			
			builder.append(format("%s IGN: %s", buyout, ign));
			return builder.toString();
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			String lineSeparator = System.lineSeparator();
			builder.append(lineSeparator);
			builder.append("id=");
			builder.append(id);
			builder.append(lineSeparator);
			builder.append("buyout=");
			builder.append(buyout);
			builder.append(lineSeparator);
			builder.append("name=");
			builder.append(name);
			builder.append(lineSeparator);
			builder.append("corrupted=");
			builder.append(corrupted);
			builder.append(lineSeparator);
			builder.append("identified=");
			builder.append(identified);
			builder.append(lineSeparator);
			builder.append("ign=");
			builder.append(ign);
			builder.append(lineSeparator);
			builder.append("socketsRaw=");
			builder.append(socketsRaw);
			builder.append(lineSeparator);
			builder.append("stackSize=");
			builder.append(stackSize);
			builder.append(lineSeparator);
			builder.append("quality=");
			builder.append(quality);
			builder.append(lineSeparator);
			builder.append("physDmgRangeAtMaxQuality=");
			builder.append(physDmgRangeAtMaxQuality);
			builder.append(lineSeparator);
			builder.append("physDmgAtMaxQuality=");
			builder.append(physDmgAtMaxQuality);
			builder.append(lineSeparator);
			builder.append("eleDmgRange=");
			builder.append(eleDmgRange);
			builder.append(lineSeparator);
			builder.append("attackSpeed=");
			builder.append(attackSpeed);
			builder.append(lineSeparator);
			builder.append("dmgAtMaxQuality=");
			builder.append(dmgAtMaxQuality);
			builder.append(lineSeparator);
			builder.append("crit=");
			builder.append(crit);
			builder.append(lineSeparator);
			builder.append("level=");
			builder.append(level);
			builder.append(lineSeparator);
			builder.append("eleDmg=");
			builder.append(eleDmg);
			builder.append(lineSeparator);
			builder.append("armourAtMaxQuality=");
			builder.append(armourAtMaxQuality);
			builder.append(lineSeparator);
			builder.append("evasionAtMaxQuality=");
			builder.append(evasionAtMaxQuality);
			builder.append(lineSeparator);
			builder.append("energyShieldAtMaxQuality=");
			builder.append(energyShieldAtMaxQuality);
			builder.append(lineSeparator);
			builder.append("block=");
			builder.append(block);
			builder.append(lineSeparator);
			builder.append("reqLvl=");
			builder.append(reqLvl);
			builder.append(lineSeparator);
			builder.append("reqStr=");
			builder.append(reqStr);
			builder.append(lineSeparator);
			builder.append("reqInt=");
			builder.append(reqInt);
			builder.append(lineSeparator);
			builder.append("reqDex=");
			builder.append(reqDex);
			builder.append(lineSeparator);
			builder.append("mapQuantity=");
			builder.append(mapQuantity);
			builder.append(lineSeparator);
			builder.append("ageAndHighLvl=");
			builder.append(ageAndHighLvl);
			builder.append(lineSeparator);
			builder.append("league=");
			builder.append(league);
			builder.append(lineSeparator);
			builder.append("seller=");
			builder.append(seller);
			builder.append(lineSeparator);
			builder.append("thread=");
			builder.append(thread);
			builder.append(lineSeparator);
			builder.append("sellerid=");
			builder.append(sellerid);
			builder.append(lineSeparator);
			builder.append("threadUrl=");
			builder.append(threadUrl);
			builder.append(lineSeparator);
			builder.append("imageUrl=");
			builder.append(imageUrl);
			builder.append(lineSeparator);
			builder.append("implicitMod=");
			builder.append(implicitMod);
			builder.append(lineSeparator);
			builder.append("explicitMods=");
			builder.append(explicitMods);
			builder.append(lineSeparator);
			builder.append("online=");
			builder.append(online);
			builder.append(lineSeparator);
			return builder.toString();
		}

		public String getId() {
			return id;
		}

		public String getBuyout() {
			String str = buyout;
			str = StringUtils.replace(str, "alteration", "alt");
			str = StringUtils.replace(str, "fusing", "fuse");
			str = StringUtils.replace(str, "jewellers", "jew");
			str = StringUtils.replace(str, "exalted", "ex");
			str = StringUtils.replace(str, "alchemy", "alch");
			str = StringUtils.replace(str, "chaos", "ch");
			return str;
		}

//		public String getName() {
//			return name;
//		}

//		public String getIgn() {
//			return ign;
//		}

//		public String getSocketsRaw() {
//			return socketsRaw;
//		}
//		
//		public String getStackSize() {
//			return stackSize;
//		}

		public String getQ() {
			return quality;
		}

		public String getAPS() {
			return attackSpeed;
		}

		public String getPDPS() {
			return physDmgAtMaxQuality;
		}
		
		public String getEDPS() {
			return eleDmg;
		}
		
		public String getDPS() {
			return dmgAtMaxQuality;
		}

		public String getEle() {
			return eleDmgRange;
		}
		
		public String getPhys() {
			return physDmgRangeAtMaxQuality;
		}

		public String getAr() {
			return armourAtMaxQuality;
		}

		public String getEv() {
			return evasionAtMaxQuality;
		}

		public String getES() {
			return energyShieldAtMaxQuality;
		}

		public String getBlk() {
			return block;
		}

		public String getCrit() {
			return crit;
		}
		
		public String getLvl() {
			return level;
		}

//		public String getReqLvl() {
//			return reqLvl;
//		}
//
//		public String getReqStr() {
//			return reqStr;
//		}
//
//		public String getReqInt() {
//			return reqInt;
//		}
//
//		public String getReqDex() {
//			return reqDex;
//		}
		
		public String getMapQty() {
			return mapQuantity;
		}

//		public String getAgeAndHighLvl() {
//			return ageAndHighLvl;
//		}
//
//		public String getLeague() {
//			return league;
//		}
//
//		public String getSeller() {
//			return seller;
//		}
//
//		public String getThread() {
//			return thread;
//		}
//
//		public String getSellerid() {
//			return sellerid;
//		}
//
//		public String getThreadUrl() {
//			return threadUrl;
//		}

//		public Mod getImplicitMod() {
//			return implicitMod;
//		}
		
//		public String getOnline() {
//			return online;
//		}
//
//		public void setOnline(String online) {
//			this.online = online;
//		}
		
//		public String getImageUrl() {
//			return imageUrl;
//		}

//		public String getPseudoEleResistance() {
//			return getExplicitModValueByName("#(pseudo) +#% total Elemental Resistance");
//		}
//
//		public String getPseudoLife() {
//			return getExplicitModValueByName("#(pseudo) (total) +# to maximum Life");
//		}
//		
//		public String getFireRes() {
//			return getExplicitModValueByName("#+#% to Fire Resistance");
//		}
//		
//		public String getColdRes() {
//			return getExplicitModValueByName("#+#% to Cold Resistance");
//		}
//		
//		public String getLightRes() {
//			return getExplicitModValueByName("#+#% to Light Resistance");
//		}
		
//		private String getExplicitModValueByName(String name) {
//			for (Mod mod : explicitMods) {
//				if (mod.getName().equalsIgnoreCase(name)) {
//					return mod.getValue();
//				}
//			}
//			return "";
//		}

		/**
		 * Used for showing exceptions in the result table.
		 */
		public static SearchResultItem exceptionItem(Exception e) {
			SearchResultItem item = new SearchResultItem();
			item.name = e.getMessage();
			item.ign = e.getClass().getName();
			item.socketsRaw = e.getCause().getMessage();
			return item;
		}

	}

}