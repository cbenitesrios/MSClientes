package com.everis.msclient.service.impl;

import org.springframework.stereotype.Service;

import com.everis.msclient.model.request.CreateClientRequest;
import com.everis.msclient.model.response.CreateClientResponse;
import com.everis.msclient.service.MSClientService;



@Service
public class MSClientServiceImpl implements MSClientService {

	@Autowired
	MSClientRepository clientrepo;
	@Override
	public CreateClientResponse createclient(CreateClientRequest createclientrequest) {
		
		return null;
	}

}
