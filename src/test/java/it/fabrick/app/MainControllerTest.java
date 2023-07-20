package it.fabrick.app;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.awt.print.Printable;
import java.io.File;

import org.junit.jupiter.api.Test;

import it.fabrick.app.controller.MainController;


public class MainControllerTest {

	
	
	 private static final int port = 8080;
	 private static final int port_wrong = 8081;
	 
	 private static String ACCOUNT_ID = "14537780";
	 private static String ACCOUNT_ID_WRONG = "145377800";
	 
	
	 
	  @Test
	  void whenGetGetCash_then_StatusCode200_andBody() throws Exception {
		  
		  given()
          	.port(port) // Specifica la porta del server locale
          .when()
          	.get("/api/v1/get-cash/"+ACCOUNT_ID)
          .then()
          	.statusCode(200) // Verifica lo stato di risposta 200
          	.body("status", equalTo("OK")) // Verifica che lo status is OK
          	.body("payload", startsWith("Current balance is")); 
		  
	  }
	  
	  
	  @Test
	  void whenGetCashWrong_then_StatusCode200_and_KO_Body() throws Exception {
		  
		  given()
          	.port(port) // Specifica la porta del server locale
          .when()
          	.get("/api/v1/get-cash/"+ACCOUNT_ID_WRONG)
          .then()
          	.statusCode(200) // Verifica lo stato di risposta 200
          	.body("status", equalTo("KO")) // Verifica che lo status is OK
          	.body("error[0].code", equalTo("REQ004")); 
		  
	  }
	  
	  @Test
	  void whenMoneyTransfer_then_StatusCode200_and_ErrorAPI000() throws Exception {
		  
		  File jsonFile = new File("moneyTrasferBody.json");
		  given()
          	.port(port) // Specifica la porta del server locale
          	.header("Content-type","application/json")
          	.body(jsonFile)
          .when()
          	.post("/api/v1/money-transfer/"+ACCOUNT_ID)
          	//.prettyPrint();
          .then()
          	.statusCode(200) // Verifica lo stato di risposta 200
          	.body("status", equalTo("KO")) // Verifica che lo status is OK
          	.body("error[0].code", equalTo("API000")) ;
		  
	  }
	  
	  
}
