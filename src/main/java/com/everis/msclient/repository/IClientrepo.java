package com.everis.msclient.repository;

import com.everis.msclient.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;  

@Repository
public interface IClientrepo extends ReactiveMongoRepository<Client, String> {
 
  Mono<Client> findByClientcode(String clientcode);
}
