package it.fabrick.app.service;

import it.fabrick.app.exception.FabrickRestException;
import it.fabrick.bean.BalanceBean;
import it.fabrick.bean.ListaTransazioniResponse;
import it.fabrick.bean.MoneyTransferRequestBody;

public interface FabrickServiceI {

	BalanceBean getBalanceBean(String accountId) throws FabrickRestException;

	String doMoneyTrasfer(String accountId, MoneyTransferRequestBody requestBody) throws FabrickRestException;

	ListaTransazioniResponse getAccountTransaction(String accountId, String fromAccountingDate, String toAccountingDate)
			throws FabrickRestException;

}