package net.thirdy.durian.util.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import net.thirdy.durian.util.Util;

public class ItemFullNamesConfig implements Config {

	@Override
	public String fileName() {
		return "itemfullnames.txt";
	}

	public List<String> loadFullNamesList() {
		try {
			List<String> list = loadLines();
			
			if (list.isEmpty()) {
				list = Arrays.asList(Util.fromClasspath(ItemFullNamesConfig.class, "default-itemfullnames.txt")
						.split(System.lineSeparator()))
						.stream()
						.filter(s -> !s.isEmpty() && !s.startsWith(";"))
						.collect(Collectors.toList());
			}
			
			return list;
		} catch (IOException | URISyntaxException e) {
			throw new ConfigException(e);
		}
	}

}
