package com.everis.msclient.model.request;

import com.everis.msclient.model.dto.ClientDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString; 
 

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DeleteClientRequest {
	private ClientDTO deleteclient;
	private String id;
}
