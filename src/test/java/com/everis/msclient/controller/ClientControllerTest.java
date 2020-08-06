package com.everis.msclient.controller;
 
import static org.mockito.Mockito.times; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters; 
import com.everis.msclient.expose.MsclientController;
import com.everis.msclient.model.Bank;
import com.everis.msclient.model.Client;
import com.everis.msclient.model.Clienttype;
import com.everis.msclient.model.request.CreateBankRequest;
import com.everis.msclient.model.request.CreateClientRequest;
import com.everis.msclient.repository.IBankrepo;
import com.everis.msclient.repository.IClientrepo;
import com.everis.msclient.repository.IClienttyperepo; 
import com.everis.msclient.service.impl.MsclientserviceImpl; 
import lombok.extern.java.Log;
import reactor.core.publisher.Mono; 

@Log 
//@SpringBootTest
//@AutoConfigureWebTestClient(timeout = "20000")  
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = MsclientController.class)
@Import(MsclientserviceImpl.class)
class ClientControllerTest {
	
	@MockBean
	IBankrepo bankrepo;
	
	@MockBean
	IClientrepo clientrepo;
	
	@MockBean
	IClienttyperepo clienttyperepo;
	
	@Autowired
	private WebTestClient webClient; 
	 
	
	
	@Test
    void createBankTest() { 
		 
	    Bank bank= new Bank();
	    bank.setId("1");
		bank.setCode("IBK");
		bank.setName("Banco Interbank");
	   
		Mockito.when(bankrepo.save(Bank.builder()
				.code("IBK")
                .name("Banco Interbank")
                .build())).thenReturn(Mono.just(bank));
			 
		
		webClient.post()
		         .uri("/apiclient/createbank")
		         .accept(MediaType.APPLICATION_JSON)
		         .body(BodyInserters.fromValue(CreateBankRequest.builder()
		        		 .code("IBK")
                         .name("Banco Interbank")
                         .build()))
		         .exchange()
		         .expectStatus().isCreated();
		
	 Mockito.verify(bankrepo, times(1)).save(Bank.builder()
				.code("IBK")
                .name("Banco Interbank")
                .build());
	}
	
	@Test
	void deleteBank(){
		Bank bank= new Bank();
	    bank.setId("1");
		bank.setCode("IBK");
		bank.setName("Banco Interbank");	
		
	 Mockito.when(bankrepo.findById("1"))
     .thenReturn(Mono.just(bank)); 

     Mono<Void> voidReturn  = Mono.empty();

     log.info(bank.toString());
     Mockito.when(bankrepo.delete(bank))
         .thenReturn(voidReturn); 

     webClient.delete()
       .uri("/apiclient/deletebank/{id}", bank.getId())  
       .exchange()
       .expectStatus().isNoContent(); 
	}
	
	@Test
	void createClientTest() {
		    Bank bank= new Bank();
		    bank.setId("1");
			bank.setCode("IBK");
			bank.setName("Banco Interbank");
		
		
		Mockito.when(bankrepo.findById("1"))
	     .thenReturn(Mono.just(bank)); 
		
		Mockito.when(clientrepo.countByClientcodeAndBankassociate("123","IBK"))
	     .thenReturn(Mono.just(0L)); 
		
		Mockito.when(clienttyperepo.findByShortdesc("PER"))
	     .thenReturn(Mono.just(Clienttype.builder()
	    		 .id("1")
	    		 .desc("PERSONAL")
	    		 .shortdesc("PER")
	    		 .build())); 
		Mockito.when(clientrepo.save(Client
                .builder()
                .name("CARLOS")
                .bankassociate("IBK")
                .clientypedesc("PERSONAL")
                .clienttype("PER")
                .clientcode("123")
                .build()));
		
		webClient.post()
        .uri("/apiclient/createclient")
        .accept(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(CreateClientRequest.builder()
        		                                 .bank("IBK")
        		                                 .name("CARLOS")
        		                                 .clienttype("PER")
        		                                 .clientcode("123")
        		                                 .build()))
        .exchange()
        .expectStatus().isCreated();
		
		Mockito.verify(clientrepo, times(1)).save(Client
                .builder()
                .name("CARLOS")
                .bankassociate("IBK")
                .clientypedesc("PERSONAL")
                .clienttype("PER")
                .clientcode("123")
                .build()); 
		
	}

	
	
	
	
	
	@Test
	void finclientTest() {
		/*Mockito.when(bankrepo.findByCode(ban.getCode())).thenReturn(Mono.just(banktest));
		
		webClient.get()
		         .uri("/apiclient/findclient") 
		         .accept(MediaType.APPLICATION_JSON)
		         .exchange()
		         .expectStatus().isOk()
		         .returnResult(Client.class)
		         .getResponseBody()
		         .map(a-> {log.info("Client " + a.toString()); return a;});
		*/
	}
	
 
}
