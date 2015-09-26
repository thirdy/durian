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
package net.thirdy.durian.backend;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import net.thirdy.durian.model.Item;
import net.thirdy.durian.util.FileUtil;
import net.thirdy.durian.util.config.ItemNamesConfig;
import net.thirdy.durian.util.config.ItemNamesConfig.ItemChoice;

/**
 *
 * @author thirdy
 */
public class BackendApiTest {

	public BackendApiTest() {
	}

	@BeforeClass
	public static void setUpClass() {
		BackendApi.setup();
	}

	@AfterClass
	public static void tearDownClass() {
		BackendApi.shutdown();
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	@Ignore
	public void getAllMappings() throws Exception {
		String result = BackendApi.getAllMappings();
		System.out.println(result);
	}

	@Test
	public void searchUnique() throws Exception {
		List<Item> result = BackendApi.searchUnique("Tempest", "Mjolner", 360);
		result.stream().forEach(e -> System.out.println(e));
	}

	@Test
	public void searchUnique2() throws Exception {
		List<Item> result = BackendApi.searchUnique("Tempest", "Tabula Rasa", 15);
		result.stream().forEach(e -> System.out.println(e));
	}

	@Test
	public void searchUnique3() throws Exception {
		List<Item> result = BackendApi.searchUnique("Tempest", "The Bringer of Rain", 500);
		result.stream().forEach(e -> System.out.println(e));
	}
	
	@Test
	public void searchUnique4() throws Exception {
		List<Item> result = BackendApi.searchUnique("Tempest", "Wurm's Molt", 500);
		result.stream().forEach(e -> System.out.println(e));
	}
	
	@Test
	public void searchUnique5() throws Exception {
		List<Item> result = BackendApi.searchUnique("Standard", "Doomsower", 5);
		result.stream().forEach(e -> System.out.println(e));
	}
	
	@Test
	public void searchStrings() throws Exception {
		String[] items = new String[] { "Healthy Mind","Brawn","Energised Armour","Ungil's Harmony","Bloodboil","Kaom's Sign","Voll's Protector","Sibyl's Lament","Apparitions","Wurm's Molt","Perandus Blazon","The Ignomon","Infernal Mantle","Lioneye's Paws","Abyssus","Army of Bones","Inertia","Doryani's Invitation","Twyzel","Kingsguard","Energy From Within","Moonsorrow","Belt of the Deceiver","Bones of Ullr","Sidhebreath","Efficient Training","Fertile Mind","Fluid Motion","Karui Ward","Araku Tiki","Careful Planning","Facebreaker","Reverberation Rod","Atziri's Promise","Slitherpinch","Bloodplay","Blackheart","Wondertrap","Flesh-Eater","Brute Force Solution","The Peregrine","Thousand Teeth Temu","Aurumvorax","Sadima's Touch","Marohi Erqi","Shavronne's Pace","Death's Harp","Relentless Fury","Lioneye's Remorse","Saffell's Frame","Abberath's Horn","Gorebreaker","Lifesprig","Deerstalker","Doedre's Tenure","Carnage Heart","Redbeak","Ungil's Gauche","Cameria's Maul","Lori's Lantern","Queen of the Forest","Nomic's Storm","The Broken Crown","Daresso's Defiance","Lakishu's Blade","Fencoil","The Princess","Geofri's Baptism","Dreadarc","Clear Mind","Sin Trek","Immortal Flesh","Prismatic Eclipse","Heartbreaker","Fragile Bloom","Doomfletch","Lioneye's Glare","The Three Dragons","Heartbound Loop","Pyre","Forbidden Taste","Ambu's Charge","Dusktoe","Malicious Intent","Mon'tregul's Grasp","Kaltenhalt","Queen's Decree","Fairgraves' Tricorne","Honourhome","Timeclasp","Al Dhih","Sundance","Chalice of Horrors","Icetomb","Rigvald's Charge","Snakebite","Mokou's Embrace","Silverbranch","Atziri's Foible","Ashrend","Shiversting","Heatshiver","Mark of the Doubting Knight","Wanderlust","Wings of Entropy","Blackgleam","Geofri's Crest","Wheel of the Stormsail","The Anvil","Hrimsorrow","Prismweave","Meginord's Girdle","Deidbell","Limbsplit","Wildslash","Lochtonial Caress","Chin Sol","Ezomyte Peak","Doedre's Scorn","Wideswing","Briskwrap","Craghead","Last Resort","Sunblast","Lightbane Raiment","Atziri's Step","Fortified Legion","Springleaf","Zahndethus' Cassock","Chitus' Apex","Mortem Morsu","Foxshade","Rise of the Phoenix","Daresso's Courage","Ondar's Clasp","Soul Mantle","Solaris Lorica","The Blood Dance","The Screaming Eagle","Eldritch Knowledge","Hyrri's Bite","Starkonja's Head","Martial Artistry","Kaom's Primacy","Hrimnor's Hymn","Rime Gaze","Malachai's Simula","Surgebinders","The Magnate","Dyadus","Voidhome","Skullhead","Ornament of the East","Infractem","Darkscorn","Meginord's Vise","Matua Tupuna","Cold Steel","The Blood Thorn","Nycta's Lantern","Atziri's Mirror","Goredrill","Hrimnor's Resolve","Rain of Splinters","Survival Secrets","Quill Rain","The Covenant","Southbound","Chernobog's Pillar","Spire of Stone","Rebuke of the Vaal","Cloak of Flame","Hotfooted","Realmshaper","Storm Cloud","Terminus Est","Moonbender's Wing","Bramblejack","The Restless Ward","Devoto's Devotion","Maligaro's Lens","Oro's Sacrifice","Asphyxia's Wrath","Cybil's Paw","Asenath's Mark","Conqueror's Efficiency","Crest of Perandus","Pugilist","Reaper's Pursuit","Thousand Ribbons","Ephemeral Edge","Mindspiral","Victario's Flight","Jaws of Agony","Leer Cast","Wake of Destruction","The Blood Reaper","The Stormheart","Survival Skills","Chober Chaber","Pillar of the Caged God","Tremor Rod","The Deep One's Hide","Quecholli","Ventor's Gamble","Darkray Vectors","The Bringer of Rain","Goldrim","Lavianga's Wisdom","Shackles of the Wretched","Static Electricity","Assassin's Haste","Crown of Thorns","Titucius' Span","Ming's Heart","Dying Breath","The Whispering Ice","Doon Cuebiyari","Tabula Rasa","Divinarius","Greed's Embrace","Apep's Rage","Doedre's Damning","The Coward's Trial","Veil of the Night","Carcass Jack","Asenath's Gentle Touch","The Searing Touch","Voidbringer","Malachai's Artifice","Thief's Torment","Doryani's Catalyst","Brightbeak","The Consuming Dark","Hyrri's Ire","Windscream","Hyaon's Fury","Warlord's Reach","Survival Instincts","Vis Mortis","Bino's Kitchen Knife","Sire of Shards","Alpha's Howl","Black Sun Crest","Maligaro's Virtuosity","Romira's Banquet","Aurseize","Belly of the Beast","Cloak of Defiance","Dream Fragments","Pledge of Hands","Stone of Lazhwar","Poacher's Aim","Taryn's Shiver","Divination Distillate","Empire's Grasp","Le Heup of All","Midnight Bargain","Rainbowstride","Kikazaru","Auxium","Goldwyrm","Rat's Nest","Cherrubim's Maleficence","Hidden Potential","Victario's Influence","Drillneck","Voltaxic Rift","Lioneye's Vision","Lioneye's Fall","Piscator's Vigil","Prism Guardian","Great Old One's Ward","Rumi's Concoction","Alberon's Warpath","Bated Breath","Call of the Brotherhood","Lightning Coil","Dyadian Dawn","Bronn's Lithe","Maligaro's Restraint","Conqueror's Potency","Eye of Chayula","Agnerod East","Bloodseeker","Doedre's Elixir","Acton's Nightmare","Shaper's Seed","Death's Oath","Maelstrom of Chaos","Rearguard","Warped Timepiece","Marylene's Fallacy","The Goddess Bound","Essentia Sanguis","Sentari's Answer","Doryani's Fist","Emberwake","Lion's Roar","Mantra of Flames","Rashkaldor's Patience","Whakawairua Tuahu","Death's Hand","Hall of Grandmasters","Hegemony's Era","Kaom's Roots","Conqueror's Longevity","Kongor's Undying Rage","Thunderfist","Andvarius","Inspired Learning","Ichimonji","Agnerod South","Null's Inclination","Jack, the Axe","Crown of Eyes","Doomsower","Astramentis","Gang's Momentum","Maloney's Nightfall","Callinellus Malleus","Kaom's Heart","Mightflay","Soul Strike","Poorjoy's Asylum","Soul Taker","Incandescent Heart","The Supreme Truth","Anatomical Knowledge","Fireborn","Rathpith Globe","Shavronne's Wrappings","Windripper","Dreamfeather","The Vertex","Agnerod North","Fidelitas' Spike","Tear of Purity","Mao Kun","Taste of Hate","Might in All Forms","Mjolner","Vaults of Atziri","Aegis Aurora","Oba's Cursed Trove","Redblade Banner","Atziri's Splendour","Scold's Bridle","Perandus Signet","Bloodgrip","Intuitive Leap","Mutewind Pennant","Olmec's Sanctum","Mutewind Whispersteps","Brinerot Flag","Brinerot Whalers","Redblade Tramplers","Void Battery","Atziri's Disfavour","Death and Taxes","Berek's Pass","Brutus' Lead Sprinkler","Brinerot Mark","Daresso's Salute","Mutewind Seal","Steppan Eard","The Goddess Scorned","Atziri's Acuity","Edge of Madness","The Gull","Vaal Caress","Broken Faith","Redblade Band","Death Rush","Ngamahu's Sign","The Pariah","The Rat Cage","Demigod's Eye","Demigod's Beacon","Gifts from Above","Lavianga's Spirit","Talisman of the Victor","Victario's Acuity","Voideye","Blood of the Karui","Crown of the Pale King","Flesh and Spirit","The Dark Seer","Berek's Grip","Kingmaker","Shadows and Dust","Shavronne's Revelation","Tasalio's Sign","Voll's Devotion" };
//		
		for (String item : items) {
			String raw = FileUtil.fromClasspath(BackendApiTest.class, "search.txt");
			raw = raw.replace("$NAME", item);
			String result = BackendApi.post("http://api.exiletools.com/index/_search?search_type=count&pretty", raw);
			String icon = StringUtils.substringBetween(result, "\"key\": \"", "\"");
			System.out.println(item + "|" + icon);
			Thread.sleep(100);
		}
	}
	
	@Test
	public void saveImages() throws Exception {
		ItemNamesConfig config = new ItemNamesConfig();
		for (ItemChoice item : config.loadNamesList()) {
			saveImage(item.getIcon());
		}
	}
	
	private void saveImage(String url) throws IOException {
		File configDirectory = new File(System.getProperty("user.home") + "\\durian-images");
		if(!configDirectory.exists()) configDirectory.mkdir();
		
		String fileName = null;

		try {
			fileName = new URL(url).toURI().getRawPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		File imageFile = new File(configDirectory, fileName);
		BufferedImage picture = null;
		if (imageFile.exists()) {
			System.out.println("Reading image from file: " + imageFile.getAbsolutePath());
			picture = ImageIO.read(imageFile);
		} else {
			System.out.println("Reading image from url: " + url.toString());
			picture = ImageIO.read(new URL(url));
			imageFile.mkdirs();
			imageFile.createNewFile();
			ImageIO.write(picture, StringUtils.substringAfterLast(fileName, "."), imageFile);
		}
	}
}
