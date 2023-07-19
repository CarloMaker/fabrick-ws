package it.fabrick.helper;

import java.text.SimpleDateFormat;

import it.fabrick.bean.BalanceBean;

public class BalanceHelper {

	public static String getFormattedBalance(BalanceBean bean) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = formatter.format(bean.getDate());
		return String.format("Current balance is %s %.3f - Updated at %s", bean.getCurrency(),
				bean.getAvailableBalance(), formattedDate);

	}
}
