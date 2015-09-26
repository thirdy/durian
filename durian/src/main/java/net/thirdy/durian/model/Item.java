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
package net.thirdy.durian.model;

import org.apache.commons.lang3.StringUtils;

/**
 * @author thirdy
 *
 */
public class Item {
	String name;
	double amount;
	String sellerIGN;
	String sellerAccount;
	String currency;
	String icon;
	String uuid;
	String threadid;
	String league;
	String modified;
	
	
	
	@Override
	public String toString() {
		return "Item [name=" + name + ", amount=" + amount + ", sellerIGN=" + sellerIGN + ", sellerAccount="
				+ sellerAccount + ", currency=" + currency + ", icon=" + icon + ", uuid=" + uuid + ", threadid="
				+ threadid + ", league=" + league + ", modified=" + modified + "]";
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	public String getThreadid() {
		return threadid;
	}
	public void setThreadid(String threadid) {
		this.threadid = threadid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getSellerIGN() {
		return sellerIGN;
	}
	public void setSellerIGN(String sellerIGN) {
		this.sellerIGN = sellerIGN;
	}
	public String getSellerAccount() {
		return sellerAccount;
	}
	public void setSellerAccount(String sellerAccount) {
		this.sellerAccount = sellerAccount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String toWTB() {
		return String.format(
				"@%s Hi, I would like to buy your %s listed for %s %s in %s",
				StringUtils.isBlank(getSellerIGN()) ? 
						getThreadid()
						: getSellerIGN(), getName(), getAmount(), getCurrency(), getLeague());
	}
	
	
}
