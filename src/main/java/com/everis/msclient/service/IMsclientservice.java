package com.everis.msclient.service;
 

import com.everis.msclient.model.Bank;
import com.everis.msclient.model.Client;
import com.everis.msclient.model.request.CreateBankRequest;
import com.everis.msclient.model.request.CreateClientRequest; 
import com.everis.msclient.model.request.UpdateClientRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMsclientservice {
   Mono<Client> createclient(CreateClientRequest createclientrequest);
   
   Mono<Client> findclient(String clientcode);
  
   Mono<Client> updateclient(UpdateClientRequest clientcode);
  
   Flux<Client> findallclient();
  
   Mono<Void> deleteclient(String clientcode);
   
   Mono<Bank> createbank(CreateBankRequest createbankrequest);
 
}
