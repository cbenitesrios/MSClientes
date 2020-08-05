package com.everis.msclient.repository;

import com.everis.msclient.model.Client;

import java.util.List;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;  

@Repository
public interface IClientrepo extends ReactiveMongoRepository<Client, String> { 
  Mono<Client> findByClientcode(String clientcode);
  Mono<Long> countByClientcodeAndBankassociate(String clientcode, String kassociate);
  Flux<Client> findByClientcodeInAndBankassociateAndClienttype(List<String> clientcode, String bankassociate, String clienttype );
}
