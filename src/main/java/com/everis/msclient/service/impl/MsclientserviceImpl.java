package com.everis.msclient.service.impl; 

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.everis.msclient.model.Bank;
import com.everis.msclient.model.Client;
import com.everis.msclient.model.Clienttype;
import com.everis.msclient.model.request.ClientListFind;
import com.everis.msclient.model.request.CreateBankRequest;
import com.everis.msclient.model.request.CreateClientRequest;
import com.everis.msclient.model.request.CreateClienttypeRequest;
import com.everis.msclient.model.request.UpdateClientRequest;
import com.everis.msclient.repository.IBankrepo;
import com.everis.msclient.repository.IClientrepo;
import com.everis.msclient.repository.IClienttyperepo;
import com.everis.msclient.service.IMsclientservice;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  
 
@Service 
public class MsclientserviceImpl implements IMsclientservice {

  @Autowired
  private IClientrepo clientrepo;
  @Autowired
  private IClienttyperepo clientyperepo;
  @Autowired
  private IBankrepo bankrepo;

  @Override
  public  Mono<Client> createclient(final CreateClientRequest cclientrequest) {   
    return    bankrepo.findByCode(cclientrequest.getBank()).switchIfEmpty(Mono.error(new Exception("Bank not found")))
    	      .then(clientrepo.countByClientcodeAndBankassociate(cclientrequest.getClientcode(), cclientrequest.getBank()))
    	      .filter(count -> count==0).switchIfEmpty(Mono.error(new Exception(MessageFormat.format("Ya es cliente en este banco: {0}",cclientrequest.getBank()))))
    	      .then(clientyperepo.findByShortdesc(cclientrequest.getClienttype()))
              .switchIfEmpty(Mono.error(new Exception("Client type not found")))
              .flatMap(type-> clientrepo.save(Client
                                        .builder()
                                        .name(cclientrequest.getName())
                                        .bankassociate(cclientrequest.getBank())
                                        .clientypedesc(type.getDesc())
                                        .clienttype(cclientrequest.getClienttype())
                                        .clientcode(cclientrequest.getClientcode())
                                        .build()));
  }
  
  @Override
  public  Mono<Bank> createbank(final CreateBankRequest createbankrequest) {   
	  return bankrepo.save(Bank.builder()
			                   .code(createbankrequest.getCode())
			                   .name(createbankrequest.getName()).build()); 
  }
  
  
  @Override
  public Mono<Client> findclient(final String id) {  
    return clientrepo.findById(id);
  }

  @Override
  public Flux<Client> findallclient() {
    return clientrepo.findAll();
  }

  @Override
  public Mono<Client> updateclient(final UpdateClientRequest updateclient) { 
    return clientrepo.findById(updateclient.getId())
                .switchIfEmpty(Mono.error(new Exception("No se pudo actualizar - id incorrecto")))
                .flatMap(client -> 
                clientrepo.save(Client.builder()
                        .id(updateclient.getId())
                        .bankassociate(updateclient.getBank())
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
  
  @Override
  public Mono<Boolean> findclientlist(ClientListFind clientlistfind){
	  return clientrepo.findByClientcodeInAndBankassociateAndClienttype(clientlistfind.getClientcode(), clientlistfind.getBankassociate(), clientlistfind.getClienttype())
			  .collectList().filter(list-> clientlistfind.getClientcode().size()==list.size()).hasElement();
  }
  
  @Override
  public Mono<Clienttype> createclientype(CreateClienttypeRequest createclienttyperequest){
	  return clientyperepo.save(Clienttype.builder().shortdesc(createclienttyperequest.getShortdesc()).desc(createclienttyperequest.getDesc()).build());
  }

}
