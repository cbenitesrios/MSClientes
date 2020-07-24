package com.everis.msclient.model.request;
  
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
public class CreateClientRequest {
	private String bank;
	private String name;
	private String clienttype;
	private String clientcode; 
}
