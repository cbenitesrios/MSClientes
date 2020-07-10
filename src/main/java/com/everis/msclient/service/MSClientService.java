package com.everis.msclient.service;
 

import com.everis.msclient.model.Client;
import com.everis.msclient.model.request.CreateClientRequest;
import com.everis.msclient.model.request.DeleteClientRequest;
import com.everis.msclient.model.request.UpdateClientRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MSClientService {
	
	public Mono<Client> createclient(CreateClientRequest createclientrequest);
	public Mono<Client> findclient(String clientcode);
	public Mono<Client> updateclient(UpdateClientRequest clientcode);
	public Flux<Client> findallclient();
	public Mono<Void> deleteclient(String clientcode);
	
	
						
}
