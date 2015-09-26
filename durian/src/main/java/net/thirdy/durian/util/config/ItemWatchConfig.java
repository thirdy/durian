package net.thirdy.durian.util.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import net.thirdy.durian.model.Currency;
import net.thirdy.durian.util.FileUtil;

public class ItemWatchConfig implements Config {

	@Override
	public String fileName() {
		return "itemwatch.txt";
	}

	public List<ItemWatch> loadItemWatchList() {
		try {
			List<ItemWatch> list = loadLines().stream()
					.filter(s -> !s.isEmpty() && !s.startsWith(";"))
					.map(e -> ItemWatch.fromConfig(e))
					.collect(Collectors.toList());
			
			if (list.isEmpty()) {
				String defaultRawConfig = FileUtil.fromClasspath(ItemWatchConfig.class, "default-itemwatch.txt");
				list = Arrays.asList(defaultRawConfig.split(System.lineSeparator()))
						.stream()
						.filter(s -> !s.isEmpty() && !s.startsWith(";"))
						.map(e -> ItemWatch.fromConfig(e))
						.collect(Collectors.toList());
				
				Files.write(Paths.get(load().toURI()), defaultRawConfig.getBytes());
			}
			
			return list;
		} catch (IOException | URISyntaxException e) {
			throw new ConfigException(e);
		}
	}
	
	public void save(List<ItemWatch> list) {
		@SuppressWarnings("unchecked")
		String s = StringUtils.join(Arrays.asList(
				"; This is a comment"
				, "; Each line represent 1 item watch"
				, "; There are 4 required information, the League, Full Item Name, Currency and Amount, separated by a pipe '|'"
				, "; These token will be trimmed, e.g. '   chaos   ' will be read as 'chaos'"
				), System.lineSeparator());
		s += System.lineSeparator() + list.stream()
				.map(i -> i.toConfig())
				.collect(Collectors.joining(System.lineSeparator()));
		try {
			Files.write(Paths.get(load().toURI()), s.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			throw new ConfigException(e);
		}
	}

	public static class ItemWatch {

		public ItemWatch() {
		}

		

		public ItemWatch(String fullName, Currency currency, String league) {
			super();
			this.fullName = fullName;
			this.currency = currency;
			this.league = league;
		}



		String fullName;
		Currency currency;
		String league;

		public static ItemWatch fromConfig(String line) {
			ItemWatch itemJob = new ItemWatch();
			String[] split = StringUtils.split(line, "|");
			int idx = 0;
			itemJob.league = split[idx++];
			itemJob.fullName = split[idx++];
			Currency currency = new Currency();
			currency.setName(split[idx++]);
			currency.setAmount(Integer.parseInt(split[idx++]));
			itemJob.currency = currency;
			return itemJob;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public Currency getCurrency() {
			return currency;
		}

		public void setCurrency(Currency currency) {
			this.currency = currency;
		}

		public String getLeague() {
			return league;
		}

		public void setLeague(String league) {
			this.league = league;
		}

		@Override
		public String toString() {
			return league + " - " + fullName + " - " + currency.getAmount() + " " + currency.getName();
		}

		public String toConfig() {
			return league + "|" + fullName + "|" + currency.getName() + "|" + currency.getAmount();
		}
	
	}

}
