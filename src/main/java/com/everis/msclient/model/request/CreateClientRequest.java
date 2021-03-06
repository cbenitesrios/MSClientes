package com.everis.msclient.model.request;
  
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter 
public class CreateClientRequest {
	@NotNull(message = "bank cant be null")
	private String bank;
	@NotNull(message = "name cant be null")
	private String name;
	@NotNull(message = "clienttype cant be null")
	private String clienttype;
	@NotNull(message = "clientcode cant be null")
	private String clientcode;  
}



