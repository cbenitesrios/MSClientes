package com.everis.msclient.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.everis.msclient.model.Bank;

import reactor.core.publisher.Mono;

public interface IBankrepo extends ReactiveMongoRepository< Bank, String>{
 
	Mono<Bank> findByCode(String code);
}
