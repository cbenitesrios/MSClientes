package com.everis.msclient.controller;
  
   
 
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.Test; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient; 
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean; 
import org.springframework.http.MediaType; 
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.everis.msclient.expose.MsclientController;
import com.everis.msclient.model.Bank;
import com.everis.msclient.model.request.CreateBankRequest;
import com.everis.msclient.service.IMsclientservice;

import ch.qos.logback.core.net.server.Client;
import lombok.extern.java.Log; 

@Log 
//@SpringBootTest
//@AutoConfigureWebTestClient(timeout = "20000") 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ClientControllerTest {
	
	@Autowired
	private WebTestClient webClient; 
	
	@Test
    void createBankTest() { 	
		  
	     webClient.post()
		         .uri("/apiclient/createbank")
		         .accept(MediaType.APPLICATION_JSON)
		         .body(BodyInserters.fromValue(CreateBankRequest.builder()
                         .code("IBK")
                         .name("Banco Interbank")
                         .build()))
		         .exchange()
		         .expectStatus().isCreated()
		         .expectBody(Bank.class) 
		         .value(c-> c.getCode(), equalTo("IBK"))
		         .value(c-> c.getName(), equalTo("Banco Interbank"));
	     
		           
	}

	@Test
	void finclientTest() {
		webClient.get()
		         .uri("/apiclient/findclient") 
		         .accept(MediaType.APPLICATION_JSON)
		         .exchange()
		         .expectStatus().isOk()
		         .returnResult(Client.class)
		         .getResponseBody()
		         .map(a-> {log.info("Client " + a.toString()); return a;});
		
	}
	
 
}
