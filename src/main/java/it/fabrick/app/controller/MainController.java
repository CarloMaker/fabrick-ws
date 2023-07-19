package it.fabrick.app.controller;



import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.fabrick.app.exception.FabrickRestException;
import it.fabrick.app.service.FabrickService;
import it.fabrick.bean.BalanceBean;
import it.fabrick.helper.BalanceHelper;
import it.fabrick.model.MoneyTransferRequestBody;
import it.fabrick.model.movimenti.ListaTransazioniResponse;
import it.fabrick.model.movimenti.Transaction;


@RestController()
public class MainController {

	Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	FabrickService mainservice;
	
	
	
	
	private static String ACCOUNT_ID="14537780";
	
	@RequestMapping(value = "/get-cash/{account-id}", method = RequestMethod.GET, produces = "application/json")
	public String getCashByAccountId(@PathVariable ("account-id") String accountId ) {
	
		try {
			
			BalanceBean balanceBean = mainservice.getBalanceBean(accountId);
			
			return  BalanceHelper.getFormattedBalance(balanceBean);
			
		} catch (FabrickRestException e) {
			return "Si è verificato un errore";
		}
	}
	
	
	
	@RequestMapping(value = "/money-transfer/{account-id}", method = RequestMethod.POST, produces = "application/json")
	public String moneyTransfer(@PathVariable ("account-id") String accountId ,@RequestBody MoneyTransferRequestBody requestBody  ) {
	
		try {
			
			String response =  mainservice.doMoneyTrasfer(accountId,requestBody);
			return response;
		} catch (FabrickRestException e) {
			logger.error("Errore" , e);
			return "Si è verificato un errore : " + e.getMessage();
		}
		
	}
	
	
	@RequestMapping(value = "/account/{account-id}/transaction", method = RequestMethod.GET, produces = "application/json")
	public String moneyTransfer(@PathVariable ("account-id") String accountId ,
			@RequestParam(required = false) String fromAccountingDate , @RequestParam(required = false) String toAccountingDate  ) {
	
		try {
			ObjectMapper mapper = new ObjectMapper();
			ListaTransazioniResponse listTransactions=  mainservice.getAccountTransaction(accountId,fromAccountingDate,toAccountingDate);
			return mapper.writeValueAsString(listTransactions);
			//return listTransactions.toString();
		} catch (FabrickRestException | JsonProcessingException e) {
			logger.error("Errore" , e);
			return "null"; 
		}
		
		
	}

	
	
	
	
	
}
