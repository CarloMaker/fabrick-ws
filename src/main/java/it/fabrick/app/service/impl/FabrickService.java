package it.fabrick.app.service.impl;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.fabrick.app.exception.ExceptionType;
import it.fabrick.app.exception.FabrickRestException;
import it.fabrick.app.repository.IRequestTransazioniRepository;
import it.fabrick.app.service.FabrickServiceI;
import it.fabrick.bean.BalanceBean;
import it.fabrick.bean.ErrorResponse;
import it.fabrick.bean.ListaTransazioniResponse;
import it.fabrick.bean.MoneyTransferRequestBody;
import it.fabrick.bean.RestOutput;
import it.fabrick.model.ListaTransazioniEntity;
import jakarta.transaction.Transactional;

@Service
public class FabrickService implements FabrickServiceI {

	private static final String API_KEY_VALUE = "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP";

	private static final String API_KEY = "Api-Key";

	private static final String S2S = "S2S";

	private static final String AUTH_SCHEMA = "Auth-Schema";

	private static final String ACCOUNT_ID = "accountId";

	private static final String EUROPE_ROME = "Europe/Rome";

	private static final String X_TIME_ZONE = "X-Time-Zone";

	Logger logger = LoggerFactory.getLogger(FabrickService.class);

	private static String BASE_URL = "https://sandbox.platfr.io";
	
	private static String ACCOUNT_PATH = "/api/gbs/banking/v4.0/accounts/{accountId}/balance";
	private static String MONEY_TRASFER = "/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
	private static String TRANSACTION_LIST = "/api/gbs/banking/v4.0/accounts/{accountId}/transactions";
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	IRequestTransazioniRepository requestTransactionRepository;
	
	private String getUrlBase() {
		return BASE_URL;
	}

	

	/**
	 * URL /api/gbs/banking/v4.0/accounts/145377801/balance 
	 */
	@Override
	public BalanceBean getBalanceBean(String accountId) throws FabrickRestException  {

		String url = new StringBuffer(getUrlBase()).append(ACCOUNT_PATH).toString();
		HttpEntity<String> request = new HttpEntity<>(getHeaders());

		UriTemplate template = new UriTemplate(url);
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put(ACCOUNT_ID, accountId);

		try {
			URI uriToCall = template.expand(uriVariables);
			logger.info("Calling getBalanceBean to {} " , uriToCall);
			ResponseEntity<RestOutput<BalanceBean>> responseEntity = restTemplate.exchange(uriToCall, HttpMethod.GET,
					request, new ParameterizedTypeReference<RestOutput<BalanceBean>>() {});
			if (responseEntity.getBody().isValid())
				return responseEntity.getBody().getPayload();
			
			throw new FabrickRestException(ExceptionType.ERR_03,"Body Invalid");

		} catch (RestClientResponseException e) {
			//invalid Header // invalid account // ( wrong changed) request  
			handleClientException(e);
			
		}
		return null;

	}

	private void handleClientException(RestClientResponseException e) throws FabrickRestException {
		//l'eccezione lavora sempre con un tipo ErrorResponse
		//se non viene passata dall'eccezione ne viene creata una (caso di path cambiato ) 
		
		ErrorResponse responseBodyAs = e.getResponseBodyAs(ErrorResponse.class);
		if(responseBodyAs != null) {
			throw new FabrickRestException(ExceptionType.ERR_02 , responseBodyAs);
		} else {
			// caso ACCOUNT_PATH cambiato
			ErrorResponse errResponse = new ErrorResponse();
			errResponse.addError(e.getStatusCode().toString(), e.getMessage());
			throw new FabrickRestException(ExceptionType.ERR_02 , errResponse);
		}
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(AUTH_SCHEMA, S2S);
		headers.add(API_KEY, API_KEY_VALUE);
		return headers;
	}

	
 
	/**
	 * VA IN ERRORE
	 * Path  /accounts/14537780/payments/money-transfers 
	 */
	@Override
	public String doMoneyTrasfer(String accountId, MoneyTransferRequestBody requestBody) throws FabrickRestException {

		HttpHeaders headers = getHeaders();
		headers.add(X_TIME_ZONE, EUROPE_ROME);

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put(ACCOUNT_ID, accountId);

		String url = new StringBuffer(getUrlBase()).append(MONEY_TRASFER).toString();
		UriTemplate template = new UriTemplate(url);
		HttpEntity<MoneyTransferRequestBody> request = new HttpEntity<>(requestBody, headers);
		try {
			URI uriToCall = template.expand(uriVariables);
			logger.info("Calling doMoneyTrasfer to {} " , uriToCall);
			ResponseEntity<String> entityResponse = restTemplate.postForEntity(uriToCall, request,
					String.class);
			
			if (!entityResponse.getStatusCode().isError())
				return entityResponse.getBody();
			
			throw new FabrickRestException(ExceptionType.ERR_03,"Body Invalid");
		} catch (RestClientResponseException e) {
			handleClientException(e);
		}
		return null;

	}

	/**
	 * URL : /accounts/14537780/transactions?fromAccountingDate=2019-04-01&toAccountingDate=2020-04-01
	 */
	@Override
	public ListaTransazioniResponse getAccountTransaction(String accountId, String fromAccountingDate,
			String toAccountingDate) throws FabrickRestException {

		HttpHeaders headers = getHeaders();
		headers.add(X_TIME_ZONE, EUROPE_ROME);

		String uri = new StringBuffer(getUrlBase()).append(TRANSACTION_LIST).toString();

		Map<String, String> uriParam = new HashMap<>();
		uriParam.put("accountId", accountId);

		UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
				.queryParam("fromAccountingDate", fromAccountingDate)
				.queryParam("toAccountingDate", toAccountingDate).build();

		HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
		
		try {
			String uriString = builder.toUriString();
			
			logger.info("Calling getAccountTransaction to {} " , uriString);

			ResponseEntity<RestOutput<ListaTransazioniResponse>> responseEntity = restTemplate.exchange(uriString,
					HttpMethod.GET, requestEntity,
					new ParameterizedTypeReference<RestOutput<ListaTransazioniResponse>>() {
					}, uriParam);
			
			if (responseEntity.getBody().isValid()) {
				persistTransactionsOperation(responseEntity.getBody().getPayload());
				return responseEntity.getBody().getPayload();
			}
				
			
			throw new FabrickRestException(ExceptionType.ERR_03,"Body Invalid");
			
		} catch (RestClientResponseException e) {
			
			handleClientException(e);

		}
		return null;
		
	}

	/**
	 * Non bloccante persistenza delle operazioni su DB
	 * @param payload
	 */
	@Transactional
	private void persistTransactionsOperation(ListaTransazioniResponse payload) {
		
		 ObjectMapper objectMapper = new ObjectMapper();
		    
		 ListaTransazioniEntity deep;
		try {
			deep = objectMapper
			      .readValue(objectMapper.writeValueAsString(payload), ListaTransazioniEntity.class);
			deep.setRequestDate(new Date());
			deep.getList().forEach(t -> t.setListaTransazioniEntity(deep)); //assign parent 
			requestTransactionRepository.save(deep);
		} catch (JsonProcessingException e) {
			logger.error("Errore persistTransactionsOperation " , e);
		}
		
		
		
	}

}
