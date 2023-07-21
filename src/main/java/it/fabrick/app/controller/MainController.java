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

import it.fabrick.app.exception.ExceptionType;
import it.fabrick.app.exception.FabrickRestException;
import it.fabrick.app.service.FabrickServiceI;
import it.fabrick.bean.BalanceBean;
import it.fabrick.bean.ListaTransazioniResponse;
import it.fabrick.bean.MoneyTransferRequestBody;
import it.fabrick.bean.RestOutput;
import it.fabrick.helper.BalanceHelper;

@RestController()
@RequestMapping("/api/v1")
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	FabrickServiceI fabrickService;

	// ACCOUNT_ID = "14537780";
	
	/**
	 * Ritorna il cash Attuale con un esempio di riformattazione del BalanceBean
	 * @param accountId 14537780
	 * @return
	 */
	@RequestMapping(value = "/get-cash/{account-id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestOutput<String>> getCashByAccountId(@PathVariable("account-id") String accountId) {

		RestOutput<String> responseOutput = null;

		logger.debug("**** getCashByAccountId : {} ", accountId);
		try {

			BalanceBean balanceBean = fabrickService.getBalanceBean(accountId);
			String formattedBalance = BalanceHelper.getFormattedBalance(balanceBean);
			responseOutput = new RestOutput<>(RestOutput.OK);
			responseOutput.setPayload(formattedBalance);
			return new ResponseEntity<>(responseOutput, HttpStatus.OK);

		} catch (FabrickRestException e) {
			logger.debug("Si è verificato un errore in getCashByAccountId : {} {} ", accountId, e.getMessage());
			responseOutput = new RestOutput<>(RestOutput.KO);
			responseOutput.setError(e.getErrorResponse().getErrors());
			return new ResponseEntity<>(responseOutput, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// UnknownHostException... Map has no value for 'accountId' ,
			logger.error("Errore Generico nella chiamata  getCashByAccountId {}", accountId, e);
			responseOutput = new RestOutput<>(RestOutput.KO);
			responseOutput.addError(ExceptionType.ERR_01.name(), e.getMessage(), "");
			return new ResponseEntity<>(responseOutput, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	/**
	 * moneyTransfer 
	 * il  "feeAccountId": "14537780", provoca l'errore  API000 - IbanBeneficiario è obbligatorio",
	 * @param accountId 14537780
	 * @param requestBody di tipo MoneyTransferRequestBody
	 * @return
	 */
	@RequestMapping(value = "/money-transfer/{account-id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<RestOutput<String>> moneyTransfer(@PathVariable("account-id") String accountId,
			@RequestBody MoneyTransferRequestBody requestBody) {

		RestOutput<String> responseOutput = null;
		try {
			logger.debug("**** moneyTransfer : {} ", accountId);
			String moneyTransferResponce = fabrickService.doMoneyTrasfer(accountId, requestBody);
			responseOutput = new RestOutput<>(RestOutput.OK);
			responseOutput.setPayload(moneyTransferResponce);
			return new ResponseEntity<>(responseOutput, HttpStatus.OK);

		} catch (FabrickRestException e) {

			logger.debug("Si è verificato un errore :" + e.getMessage());
			responseOutput = new RestOutput<>(RestOutput.KO);
			responseOutput.setError(e.getErrorResponse().getErrors());
			return new ResponseEntity<>(responseOutput, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {

			logger.error("Errore durante la chiamata  MoneyTransfer :", e);
			responseOutput = new RestOutput<>(RestOutput.KO);
			responseOutput.addError(ExceptionType.ERR_01.name(), e.getMessage(), "");
			return new ResponseEntity<>(responseOutput, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/account/{account-id}/transaction", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<RestOutput<ListaTransazioniResponse>> getTransactions(
			@PathVariable("account-id") String accountId, @RequestParam(required = false) String fromAccountingDate,
			@RequestParam(required = false) String toAccountingDate) {

		RestOutput<ListaTransazioniResponse> responseOutPut = null;
		try {
			logger.debug("**** getTransactions : {} ", accountId);
			ListaTransazioniResponse listTransactions = fabrickService.getAccountTransaction(accountId,
					fromAccountingDate, toAccountingDate);
			responseOutPut = new RestOutput<>(RestOutput.OK);
			responseOutPut.setPayload(listTransactions);
			return new ResponseEntity<>(responseOutPut, HttpStatus.OK);

		} catch (FabrickRestException e) {

			logger.debug("GetTransactions FabrickRestException nella chiamata :" + e.getMessage());
			responseOutPut = new RestOutput<>(RestOutput.KO);
			responseOutPut.setError(e.getErrorResponse().getErrors());
			return new ResponseEntity<>(responseOutPut, HttpStatus.BAD_REQUEST);

		} catch (Exception e) {

			logger.error("Errore durante la chiamata  GetTransactions :", e);
			responseOutPut = new RestOutput<>(RestOutput.KO);
			responseOutPut.addError(ExceptionType.ERR_01.name(), e.getMessage(), "");
			return new ResponseEntity<>(responseOutPut, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
