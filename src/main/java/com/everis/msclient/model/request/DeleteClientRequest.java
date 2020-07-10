package com.everis.msclient.model.request;

import com.everis.msclient.model.dto.ClientDto; 
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
  private ClientDto deleteclient;
  private String id;
}
