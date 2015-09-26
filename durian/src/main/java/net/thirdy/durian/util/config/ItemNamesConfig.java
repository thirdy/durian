package net.thirdy.durian.util.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import net.thirdy.durian.util.FileUtil;

public class ItemNamesConfig implements Config {

	@Override
	public String fileName() {
		return "itemnames.txt";
	}

	public List<String> loadNamesList() {
		try {
			List<String> list = loadLines().stream()
					.filter(s -> !s.isEmpty() && !s.startsWith(";"))
					.collect(Collectors.toList());;
			
			if (list.isEmpty()) {
				String defaultRawConfig = loadDefault();
				list = Arrays.asList(defaultRawConfig.split(System.lineSeparator()))
						.stream()
						.filter(s -> !s.isEmpty() && !s.startsWith(";"))
						.collect(Collectors.toList());
				
				Files.write(Paths.get(load().toURI()), defaultRawConfig.getBytes());
			}
			
			return list;
		} catch (IOException | URISyntaxException e) {
			throw new ConfigException(e);
		}
	}

	private String loadDefault() throws IOException, URISyntaxException {
		return FileUtil.fromClasspath(ItemNamesConfig.class, "default-itemnames.txt");
	}

}
