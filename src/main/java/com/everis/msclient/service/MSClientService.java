package com.everis.msclient.service;

import com.everis.msclient.model.request.CreateClientRequest;
import com.everis.msclient.model.response.CreateClientResponse;

public interface MSClientService {
	
	public CreateClientResponse createclient(CreateClientRequest createclientrequest);

}
