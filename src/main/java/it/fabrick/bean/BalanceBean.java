package it.fabrick.bean;

import java.io.Serializable;
import java.util.Date;

public class BalanceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1036763932859558917L;
	
	private Date date;
	private float  balance;
	private float availableBalance;
	private String currency;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public float getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(float availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BalanceBean [date=");
		builder.append(date);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", availableBalance=");
		builder.append(availableBalance);
		builder.append(", currency=");
		builder.append(currency);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	

}
