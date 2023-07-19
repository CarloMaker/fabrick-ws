package it.fabrick.app.service;


import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.fabrick.app.exception.FabrickRestException;
import it.fabrick.bean.BalanceBean;
import it.fabrick.bean.RestOutput;
import it.fabrick.model.ErrorResponse;
import it.fabrick.model.MoneyTransferRequestBody;
import it.fabrick.model.movimenti.ListaTransazioniResponse;
import it.fabrick.model.movimenti.Transaction;

@Service
public class FabrickService {
	
	private static final String ACCOUNT_ID = "accountId";

	private static final String EUROPE_ROME = "Europe/Rome";

	private static final String X_TIME_ZONE = "X-Time-Zone";

	Logger logger = LoggerFactory.getLogger(FabrickService.class);
	
	private static String BASE_URL="https://sandbox.platfr.io";
	private static String ACCOUNT_PATH ="/api/gbs/banking/v4.0/accounts/{accountId}/balance";
	private static String MONEY_TRASFER= "/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
	private static String TRANSACTION_LIST= "/api/gbs/banking/v4.0/accounts/{accountId}/transactions";
	@Autowired
	RestTemplate restTemplate;
	
	private String getUrlBase() {
		return BASE_URL;
	}
	
	private void addAuthHeaders(HttpHeaders headers) {
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Auth-Schema", "S2S");
		headers.add("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
		
	}
	
	public BalanceBean getBalanceBean(String accountId) throws FabrickRestException {
	
		HttpHeaders headers = new HttpHeaders();
		addAuthHeaders(headers);
		
		String url = new StringBuffer(getUrlBase()).append(ACCOUNT_PATH).toString();		
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		UriTemplate template = new UriTemplate(url);
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put(ACCOUNT_ID, accountId);
		
		try {
			URI uriToCall = template.expand(uriVariables);
			
			logger.info("Calling " +  uriToCall);
			ResponseEntity <RestOutput<BalanceBean>> responseEntity= restTemplate.exchange(uriToCall, HttpMethod.GET, request, new ParameterizedTypeReference<RestOutput<BalanceBean>>(){});

			if(responseEntity.getBody().isValid())
				return responseEntity.getBody().getPayload(); 
			return null;
		} catch (RestClientResponseException e  ) {
			
			logger.error("Errore durante la chiamata  getCash :" , e );
			throw new FabrickRestException(e.getMessage());
		}
		
	}

	public String doMoneyTrasfer(String accountId,MoneyTransferRequestBody requestBody) throws FabrickRestException {
		
		HttpHeaders headers = new HttpHeaders();
		addAuthHeaders(headers);
		headers.add(X_TIME_ZONE, EUROPE_ROME);
		
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put(ACCOUNT_ID, accountId);
		
		String url = new StringBuffer(getUrlBase()).append(MONEY_TRASFER).toString();
		UriTemplate template = new UriTemplate(url);
		HttpEntity<MoneyTransferRequestBody> request = new HttpEntity<>(requestBody, headers);
		try {
			ResponseEntity<String>  entityResponse = restTemplate.postForEntity(template.expand(uriVariables), request, String.class);
			if(entityResponse.getStatusCode() == HttpStatus.OK) {
				return "OK";
			}
			return "KO";
				
		} catch (RestClientResponseException e) {
			logger.error(e.getMessage(),e);
			ErrorResponse responseBodyAs = e.getResponseBodyAs(ErrorResponse.class);	
			throw new FabrickRestException(responseBodyAs.getErrors().toString());
		}
		
	}

	public ListaTransazioniResponse getAccountTransaction(String accountId, String fromAccountingDate,
			String toAccountingDate) throws FabrickRestException {
		
		HttpHeaders headers = new HttpHeaders();
		addAuthHeaders(headers);
		headers.add(X_TIME_ZONE, EUROPE_ROME);
		
		String uri =  new StringBuffer(getUrlBase()).append(TRANSACTION_LIST).toString();
				
	    Map<String, String> uriParam = new HashMap<>();
	    uriParam.put("accountId",accountId);

	    UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
	                .queryParam("fromAccountingDate",fromAccountingDate)
	                        .queryParam("toAccountingDate",toAccountingDate)
	                                                .build();
	    
	    HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
	    try {
		    String uriString = builder.toUriString();
		    logger.info("uriString " + uriString);
			ResponseEntity<ListaTransazioniResponse> response = restTemplate.exchange(uriString,HttpMethod.GET, requestEntity,
		    		ListaTransazioniResponse.class,uriParam);
			
			ResponseEntity <RestOutput<ListaTransazioniResponse>> responseEntity= restTemplate.exchange(uriString, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<RestOutput<ListaTransazioniResponse>>(){},uriParam);
			if(responseEntity.getBody().isValid())
				return responseEntity.getBody().getPayload(); 
		    return null;
	    } catch (RestClientResponseException e) {
	    	e.printStackTrace();
	    	throw  new FabrickRestException(e.getResponseBodyAsString());
	    	
	    }
	}
	
	

	
}
