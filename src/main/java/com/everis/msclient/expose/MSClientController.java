package com.everis.msclient.expose; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everis.msclient.model.request.CreateClientRequest;
import com.everis.msclient.model.response.CreateClientResponse;
import com.everis.msclient.service.MSClientService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apiclient")
public class MSClientController {

@Autowired
private MSClientService msclientservice;

@PutMapping("/createclient")
public Mono<CreateClientResponse> createClient(@RequestBody CreateClientRequest createclientrequest){
	return Mono.justOrEmpty(msclientservice.createclient(createclientrequest));
}

@PutMapping("/createclient")
public Mono<CreateClientResponse> createClient(@RequestBody CreateClientRequest createclientrequest){
	return Mono.justOrEmpty(msclientservice.createclient(createclientrequest));
}
	
	
}
