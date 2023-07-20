package it.fabrick.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.fabrick.app.exception.FabrickRestException;
import it.fabrick.app.service.FabrickServiceI;
import it.fabrick.bean.BalanceBean;
import it.fabrick.bean.ExceptionType;
import it.fabrick.bean.RestOutput;
import it.fabrick.helper.BalanceHelper;
import it.fabrick.model.MoneyTransferRequestBody;
import it.fabrick.model.movimenti.ListaTransazioniResponse;

@RestController()
@RequestMapping("/api/v1")
public class MainController {

	Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	FabrickServiceI fabrickService;

	private static String ACCOUNT_ID = "14537780";

	@RequestMapping(value = "/get-cash/{account-id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestOutput<String>> getCashByAccountId(@PathVariable("account-id") String accountId) {
		RestOutput<String> responseOutput = null;
		try {

			BalanceBean balanceBean = fabrickService.getBalanceBean(accountId);
			String formattedBalance = BalanceHelper.getFormattedBalance(balanceBean);
			responseOutput = new RestOutput<>(RestOutput.OK);
			responseOutput.setPayload(formattedBalance);
			return new ResponseEntity<>(responseOutput, HttpStatus.OK);

		} catch (FabrickRestException e) {
			logger.debug("Si è verificato un errore in getCashByAccountId :" + e.getMessage());
			responseOutput = new RestOutput<>(RestOutput.KO);
			responseOutput.setError(e.getErrorResponse().getErrors());
			return new ResponseEntity<>(responseOutput, HttpStatus.OK);

		} catch (Exception e) {
			// UnknownHostException... Map has no value for 'accountId' , 
			logger.error("Errore Generico nella chiamata  getCashByAccountId :", e);
			responseOutput = new RestOutput<>(RestOutput.KO);
			responseOutput.addError( ExceptionType.ERR_01.name(),  e.getMessage(),"");
			return new ResponseEntity<>(responseOutput, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/money-transfer/{account-id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestOutput<String>> moneyTransfer(@PathVariable("account-id") String accountId,
			@RequestBody MoneyTransferRequestBody requestBody) {

		RestOutput<String> responseOutput = null;
		try {

			String moneyTransferResponce = fabrickService.doMoneyTrasfer(accountId, requestBody);
			responseOutput = new RestOutput<>(RestOutput.OK);
			responseOutput.setPayload(moneyTransferResponce);
			return new ResponseEntity<>(responseOutput, HttpStatus.OK);

		} catch (FabrickRestException e) {
			
			logger.debug("Si è verificato un errore :" + e.getMessage());
			responseOutput = new RestOutput<>(RestOutput.KO);
			responseOutput.setError(e.getErrorResponse().getErrors());
			return new ResponseEntity<>(responseOutput, HttpStatus.OK);
			
		} catch (Exception e) {
			
			logger.error("Errore durante la chiamata  MoneyTransfer :", e);
			responseOutput = new RestOutput<>(RestOutput.KO);
			responseOutput.addError( ExceptionType.ERR_01.name(),  e.getMessage(),"");
			return new ResponseEntity<>(responseOutput, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/account/{account-id}/transaction", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestOutput<ListaTransazioniResponse>> moneyTransfer(
			@PathVariable("account-id") String accountId, @RequestParam(required = false) String fromAccountingDate,
			@RequestParam(required = false) String toAccountingDate) {

		RestOutput<ListaTransazioniResponse> responseOutPut = null;
		try {

			ListaTransazioniResponse listTransactions = fabrickService.getAccountTransaction(accountId, fromAccountingDate,
					toAccountingDate);
			responseOutPut = new RestOutput<>(RestOutput.OK);
			responseOutPut.setPayload(listTransactions);
			return new ResponseEntity<>(responseOutPut, HttpStatus.OK);

		} catch (FabrickRestException e) {
			
			logger.debug("Si è verificato un errore :" + e.getMessage());
			responseOutPut = new RestOutput<>(RestOutput.KO);
			responseOutPut.setError(e.getErrorResponse().getErrors());
			return new ResponseEntity<>(responseOutPut, HttpStatus.OK);
			
		} catch (Exception e) {
			
			logger.error("Errore durante la chiamata  MoneyTransfer :", e);
			responseOutPut = new RestOutput<>(RestOutput.KO);
			responseOutPut.addError( ExceptionType.ERR_01.name(),  e.getMessage(),"");
			return new ResponseEntity<>(responseOutPut, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
