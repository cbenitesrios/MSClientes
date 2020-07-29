package com.everis.msclient.expose;

import com.everis.msclient.model.Client;
import com.everis.msclient.model.dto.ErrorDto;
import com.everis.msclient.model.request.CreateClientRequest;
import com.everis.msclient.model.request.UpdateClientRequest; 
import com.everis.msclient.service.IMsclientservice;
import com.netflix.ribbon.proxy.annotation.Http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono; 
 
@RestController
@RequestMapping("/apiclient")
public class MsclientController {


  //@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler
  public Mono<ErrorDto> exception(ServerHttpResponse response, Exception request) {
	 response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
    return Mono.just(new ErrorDto(request.getLocalizedMessage(), request.getMessage()));
  } 
	
  @Autowired
  private IMsclientservice msclientservice;

  @PostMapping("/createclient")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Mono<Client> createClient(@RequestBody final CreateClientRequest cclientrequest) {
    return msclientservice.createclient(cclientrequest);
  }  
  
  @GetMapping("/findclient/{clientcode}")
  public Mono<Client> findclient(@PathVariable final String clientcode) {
    return msclientservice.findclient(clientcode);
  }

  @GetMapping("/findclient") 
  public Flux<Client> findclient() {
    return msclientservice.findallclient();
  }

  @PutMapping("/updateclient")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Mono<Client> updateclient(@RequestBody final UpdateClientRequest updateclient) {
    return msclientservice.updateclient(updateclient);
  }

  @DeleteMapping("/deleteclient/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public Mono<Void> deleteclient(@PathVariable final String id) {
    return msclientservice.deleteclient(id);
  }
   
  
}
