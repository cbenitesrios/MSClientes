package com.everis.msclient.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.everis.msclient.model.Clienttype;

import reactor.core.publisher.Mono; 
public interface IClienttyperepo extends ReactiveMongoRepository<Clienttype, String> { 
	Mono<Clienttype> findByShortdesc(String shortdesc);
}
