package it.fabrick.app;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.io.File;

import org.junit.jupiter.api.Test;


public class MainControllerTest {

	
	
	 private static final int port = 8080;
	 private static String ACCOUNT_ID = "14537780";
	 private static String ACCOUNT_ID_WRONG = "145377800";
	 
	
	 
	  @Test
	  void whenGetCash_then_StatusCode200_andCheckBody() throws Exception {
		  
		  given()
          	.port(port) // Specifica la porta del server locale
          .when()
          	.get("/api/v1/get-cash/{account-id}",ACCOUNT_ID)
          .then()
          	.statusCode(200) // Verifica lo stato di risposta 200
          	.body("status", equalTo("OK")) // Verifica che lo status is OK
          	.body("payload", startsWith("Current balance is")); 
		  
	  }
	  
	  
	  @Test
	  void whenGetCashWrong_then_StatusCode400_and_CheckErrorREQ004() throws Exception {
		  
		  given()
          	.port(port) // Specifica la porta del server locale
          .when()
          	.get("/api/v1/get-cash/{account-id}",ACCOUNT_ID_WRONG)
          .then()
          	.statusCode(400) // Verifica lo stato di risposta 200
          	.body("status", equalTo("KO")) // Verifica che lo status is OK
          	.body("error[0].code", equalTo("REQ004")); 
		  
	  }
	  
	  @Test
	  void whenMoneyTransfer_then_StatusCode400_and_CheckErrorAPI000() throws Exception {
		  
		  File jsonFile = new File("moneyTrasferBody.json");
		  given()
          	.port(port) // Specifica la porta del server locale
          	.header("Content-type","application/json")
          	.body(jsonFile)
          .when()
          	.post("/api/v1/money-transfer/"+ACCOUNT_ID)
          .then()
          	.statusCode(400) // Verifica lo stato di risposta 200
          	.body("status", equalTo("KO")) // Verifica che lo status is OK
          	.body("error[0].code", equalTo("API000")) ;
		  
	  }
	  
	  @Test
	  void whengetGetTransactions_WithWrongID_then_KO_StatusCode400_andCheckErrorREQ004() throws Exception {
		  
		  given()
          	.port(port) // Specifica la porta del server locale
          	 .queryParam("fromAccountingDate", "2019-01-31")
          	 .queryParam("toAccountingDate", "2019-01-31")
          .when()
          	.get("/api/v1/account/{account-id}/transaction",ACCOUNT_ID_WRONG)
          .then()
          	.statusCode(400) // Verifica lo stato di risposta 200
          	.body("status", equalTo("KO")) // Verifica che lo status is OK
          	.body("error[0].code", equalTo("REQ004")) ;// Verifica errore REQ004
		  
	  }
	  
	  @Test
	  void whengetGetTransactions_Single_then_OK_StatusCode200_andCheckSingle() throws Exception {
		  
		  given()
          	.port(port) // Specifica la porta del server locale
          	 .queryParam("fromAccountingDate", "2019-01-31")
          	 .queryParam("toAccountingDate", "2019-01-31")
          .when()
          	.get("/api/v1/account/{account-id}/transaction",ACCOUNT_ID)
          .then()
          	.statusCode(200) // Verifica lo stato di risposta 200
          	.body("status", equalTo("OK")) // Verifica che lo status is OK
          	.body("payload.list[0].transactionId", equalTo("398894")) // Verifica che lo status is OK
          	
          	.body("payload.list.size()",  equalTo(1)); 
		  
	  }
	  
	  
	  @Test
	  void whengetGetTransactions_Multi_then_KO_StatusCode200_andCheckSize() throws Exception {
		  
		  given()
          	.port(port) // Specifica la porta del server locale
          	 .queryParam("fromAccountingDate", "2019-01-31")
          	 .queryParam("toAccountingDate", "2020-01-31")
          .when()
          	.get("/api/v1/account/{account-id}/transaction",ACCOUNT_ID)
          .then()
          	.statusCode(200) // Verifica lo stato di risposta 200
          	.body("status", equalTo("OK")) // Verifica che lo status is OK
          	.body("payload.list.size()",  equalTo(20)); // la lista e' maggiore di 1
		  
	  }
	  
	  
	  
	  
}
