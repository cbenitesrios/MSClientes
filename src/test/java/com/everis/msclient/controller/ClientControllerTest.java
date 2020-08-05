package com.everis.msclient.controller;
  
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.everis.msclient.expose.MsclientController;
import com.everis.msclient.model.Bank;
import com.everis.msclient.model.request.CreateBankRequest;
import com.everis.msclient.service.IMsclientservice;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import reactor.core.publisher.Mono; 

@Log
@RunWith(SpringRunner.class)
@WebFluxTest
class ClientControllerTest {
	
	@Autowired
	private WebTestClient webClient; 
	
	@MockBean
    private IMsclientservice clientservice; 	
	@Test
    void createBankTest() { 	
		  
		 System.out.println(webClient.post()
		         .uri("/apiclient/createbank")
		         .accept(MediaType.APPLICATION_JSON)
		         .body(BodyInserters.fromValue(CreateBankRequest.builder()
                         .code("IBK")
                         .name("Banco Interbank")
                         .build()))
		         .exchange()
		         .expectStatus().isCreated() 
		         .returnResult(Bank.class)
		         .getResponseBody()
		         .map(a-> {log.info("BANK " + a.toString()); return a;}));
		
	 ;
	}

	@Test
	void finclientTest() {
		webClient.get()
		         .uri("/apiclient/findclient") 
		         .accept(MediaType.APPLICATION_JSON)
		         .exchange()
		         .expectStatus().isOk()
		         .expectBody()
		         .consumeWith(response ->
                 Assertions.assertThat(response.getResponseBody()).isNotNull());;
		
	}
	
 
}
