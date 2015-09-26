package net.thirdy.durian.util.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import net.thirdy.durian.model.Currency;
import net.thirdy.durian.util.Util;

public class ItemWatchConfig implements Config {

	@Override
	public String fileName() {
		return "itemwatch.txt";
	}

	public List<ItemWatch> loadItemWatchList() {
		try {
			List<ItemWatch> list = loadLines().stream().map(e -> ItemWatch.fromConfig(e)).collect(Collectors.toList());
			
			if (list.isEmpty()) {
				list = Arrays.asList(Util.fromClasspath(ItemWatchConfig.class, "default-itemwatch.txt").split(System.lineSeparator()))
						.stream()
						.filter(s -> !s.isEmpty() && !s.startsWith(";"))
						.map(e -> ItemWatch.fromConfig(e))
						.collect(Collectors.toList());
			}
			
			return list;
		} catch (IOException | URISyntaxException e) {
			throw new ConfigException(e);
		}
	}

	public static class ItemWatch {

		public ItemWatch() {
		}

		public ItemWatch(String fullName, Currency currency) {
			this.fullName = fullName;
			this.currency = currency;
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
	}

}
