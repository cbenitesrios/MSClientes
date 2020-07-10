package com.everis.msclient.repository;
 
 

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository; 
import com.everis.msclient.model.Client;

import reactor.core.publisher.Mono;

@Repository
public interface MSClientRepository extends ReactiveMongoRepository<Client, String>{   
	public Mono<Client> findByClientcode(String clientcode); 

}
