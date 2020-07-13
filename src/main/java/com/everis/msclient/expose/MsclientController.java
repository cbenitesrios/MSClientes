package com.everis.msclient.expose;

import com.everis.msclient.model.Client;
import com.everis.msclient.model.request.CreateClientRequest;
import com.everis.msclient.model.request.UpdateClientRequest; 
import com.everis.msclient.service.MsclientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.web.bind.annotation.DeleteMapping;
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

  @Autowired
private MsclientService msclientservice;

  @PostMapping("/createclient")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Mono<Client> createClient(@RequestBody CreateClientRequest createclientrequest) {
    return msclientservice.createclient(createclientrequest);
  }

  @GetMapping("/findclient/{clientcode}")
  public Mono<Client> findclient(@PathVariable String clientcode) {
    return msclientservice.findclient(clientcode);
  }

  @GetMapping("/findclient") 
  public Flux<Client> findclient() {
    return msclientservice.findallclient();
  }

  @PutMapping("/updateclient")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Mono<Client> updateclient(@RequestBody UpdateClientRequest updateclient) {
    return msclientservice.updateclient(updateclient);
  }

  @DeleteMapping("/deleteclient/{clientcode}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public Mono<Void> deleteclient(@PathVariable String clientcode) {
    return msclientservice.deleteclient(clientcode);
  }
}