package net.thirdy.durian.util.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import net.thirdy.durian.util.FileUtil;

public class ItemNamesConfig implements Config {

	@Override
	public String fileName() {
		return "itemnames.txt";
	}
	
	private static final String regexString = "^;.+|.+";

	public List<ItemChoice> loadNamesList() {
		try {
			List<ItemChoice> list = loadLines().stream()
					.filter(s -> !s.isEmpty() && !s.startsWith(";"))
					.map(s -> ItemChoice.fromConfig(s))
					.collect(Collectors.toList());
			
			if (list.isEmpty()) {
				String defaultRawConfig = loadDefault();
				list = Arrays.asList(defaultRawConfig.split(System.lineSeparator()))
						.stream()
						.filter(s -> s.matches(regexString))
						.map(s -> ItemChoice.fromConfig(s))
						.collect(Collectors.toList());
				
				Files.write(Paths.get(load().toURI()), defaultRawConfig.getBytes());
			}
			
			return list;
		} catch (IOException | URISyntaxException e) {
			throw new ConfigException(e);
		}
	}
	
	public static class ItemChoice {
		String name;
		String icon;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public static ItemChoice fromConfig(String line) {
			ItemChoice item = new ItemChoice();
			String[] split = line.split("\\|");
			item.setName(split[0]);
			item.setIcon(split[1]);
			return item;
		}
		
		@Override
		public String toString() {
			return name;
		}
		
	} 

	private String loadDefault() throws IOException, URISyntaxException {
		return FileUtil.fromClasspath(ItemNamesConfig.class, "default-itemnames.txt");
	}

}
