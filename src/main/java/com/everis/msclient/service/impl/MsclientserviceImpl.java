package com.everis.msclient.service.impl;

import com.everis.msclient.model.Client; 
import com.everis.msclient.model.request.CreateClientRequest; 
import com.everis.msclient.model.request.UpdateClientRequest; 
import com.everis.msclient.repository.IClientrepo;
import com.everis.msclient.repository.IClienttyperepo;
import com.everis.msclient.service.IMsclientservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
@Service 
public class MsclientserviceImpl implements IMsclientservice {

  @Autowired
  private IClientrepo clientrepo;
  @Autowired
  private IClienttyperepo clientyperepo;
  	

  @Override
  public  Mono<Client> createclient(final CreateClientRequest cclientrequest) {   
    return    clientyperepo.findByShortdesc(cclientrequest.getClienttype())
              .switchIfEmpty(Mono.error(new Exception("Client type not found")))
              .flatMap(type-> clientrepo.save(Client
                                        .builder()
                                        .name(cclientrequest.getName())
                                        .clientypedesc(type.getDesc())
                                        .clienttype(cclientrequest.getClienttype())
                                        .clientcode(cclientrequest.getClientcode())
                                        .build()));
  }
  
  @Override
  public Mono<Client> findclient(final String clientcode) {  
    return clientrepo.findByClientcode(clientcode)
                     .switchIfEmpty(Mono.error(new Exception("No se encontro entidad")));
  }

  @Override
  public Flux<Client> findallclient() {
    return clientrepo.findAll()
                     .switchIfEmpty(Mono.error(new Exception("No se encontraron resultados")));
  }

  @Override
  public Mono<Client> updateclient(final UpdateClientRequest updateclient) { 
    return clientrepo.findById(updateclient.getId())
                .switchIfEmpty(Mono.error(new Exception("No se pudo actualizar")))
                .flatMap(client -> 
                clientrepo.save(Client.builder()
                        .id(updateclient.getId())
                        .bank(updateclient.getBank())
                        .name(updateclient.getName()) 
                        .clienttype(updateclient.getClienttype())
                        .clientcode(updateclient.getClientcode())
                        .build()));  
  }

  @Override
  public Mono<Void> deleteclient(final String id) {
    return clientrepo.findById(id)
            .switchIfEmpty(Mono.error(new Exception("No se encontro entidad")))
            .flatMap(clientrepo::delete); 
  } 

}
