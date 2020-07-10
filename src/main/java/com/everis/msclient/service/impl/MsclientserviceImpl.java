package com.everis.msclient.service.impl;

import com.everis.msclient.model.Client; 
import com.everis.msclient.model.request.CreateClientRequest; 
import com.everis.msclient.model.request.UpdateClientRequest; 
import com.everis.msclient.repository.MsclientRepository; 
import com.everis.msclient.service.MsclientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
@Service
public class MsclientserviceImpl implements MsclientService {

  @Autowired
  private MsclientRepository clientrepo;

  @Override
  public  Mono<Client> createclient(CreateClientRequest createclientrequest) {   
    return clientrepo.findByClientcode(createclientrequest.getClientrequest().getClientcode())
                     .defaultIfEmpty(new Client(null,
                                        createclientrequest.getClientrequest().getFirstname(),
                                        createclientrequest.getClientrequest().getLastname(),
                                        createclientrequest.getClientrequest().getClienttype(),
                                        createclientrequest.getClientrequest().getClientcode() 
                                        )).flatMap(clientrepo::save);
  }
  
  @Override
  public Mono<Client> findclient(String clientcode) { 
    return clientrepo.findByClientcode(clientcode)
                     .switchIfEmpty(Mono.error(new Exception("No se encontro entidad")));
  }

  @Override
  public Flux<Client> findallclient() {
    return clientrepo.findAll()
                     .switchIfEmpty(Mono.error(new Exception("No se encontrontraron resultados")));
  }

  @Override
  public Mono<Client> updateclient(UpdateClientRequest updateclient) { 
    return  clientrepo.findById(updateclient.getId())
                .switchIfEmpty(Mono.error(new Exception("No se pudo actualizar")))
                .flatMap(client -> 
                clientrepo.save(Client.builder()
                        .id(updateclient.getId())
                        .firstname(updateclient.getUpdateclient().getFirstname())
                        .lastname(updateclient.getUpdateclient().getLastname())
                        .clientcode(updateclient.getUpdateclient().getClientcode())
                        .clienttype(updateclient.getUpdateclient().getClienttype()).build()));  
  }

  @Override
  public Mono<Void> deleteclient(String clientcode) {
    return clientrepo.findByClientcode(clientcode)
            .switchIfEmpty(Mono.error(new Exception("No se encontro entidad")))
            .flatMap(clientrepo::delete); 
  } 

}
