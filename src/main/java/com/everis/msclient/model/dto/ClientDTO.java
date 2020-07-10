package com.everis.msclient.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClientDTO { 
	 private String firstname;
	 private String lastname;
	 private String clienttype;
	 private String clientcode;
}
