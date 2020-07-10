package com.everis.msclient.model.response;

import com.everis.msclient.model.Client; 
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateClientResponse {
  private Client clientresponse;
}
