package com.everis.msclient.expose;

import org.springframework.beans.factory.annotation.Autowired;
import com.everis.msclient.model.Bank;
import com.everis.msclient.model.Client;
import com.everis.msclient.model.Clienttype;
import com.everis.msclient.model.dto.ErrorDto;
import com.everis.msclient.model.request.ClientListFind;
import com.everis.msclient.model.request.CreateBankRequest;
import com.everis.msclient.model.request.CreateClientRequest; 
import com.everis.msclient.model.request.CreateClienttypeRequest;
import com.everis.msclient.model.request.UpdateClientRequest; 
import com.everis.msclient.service.IMsclientservice;  
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

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono; 
 
@RestController
@RequestMapping("/apiclient")
public class MsclientController {


  @ExceptionHandler
  public Mono<ErrorDto> exception(ServerHttpResponse response, Exception request) {
	 response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
    return Mono.just(new ErrorDto(request.getLocalizedMessage(), request.getMessage()));
  } 
	
  @Autowired
  private IMsclientservice msclientservice;

  /*creacion de clientes*/
  @PostMapping("/createclient")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Mono<Client> createClient(@RequestBody final CreateClientRequest cclientrequest) {
    return msclientservice.createclient(cclientrequest);
  }  
  
  /*crear bancos*/
  @PostMapping("/createbank")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Mono<Bank> createbank(@RequestBody final CreateBankRequest bankrequest) {
    return msclientservice.createbank(bankrequest);
  }
  
  /*crear tipo de clientes*/
  @PostMapping("/createclientype")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Mono<Clienttype> createclientype(@RequestBody final CreateClienttypeRequest typerequest) {
    return msclientservice.createclientype(typerequest);
  }   
  
  /*busqueda de clientes*/
  @GetMapping("/findclient/{id}")
  public Mono<Client> findclient(@PathVariable final String id) {
    return msclientservice.findclient(id);
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
  
  @PostMapping("/findclientlist") 
  public Mono<Boolean> findclient(@RequestBody ClientListFind clientlistrequest) {
    return msclientservice.findclientlist(clientlistrequest);
  }
   
  
}
