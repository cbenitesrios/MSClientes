package com.everis.msclient.model.request;
 
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull; 
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
public class UpdateClientRequest {
	
    @NotNull(message = "id")
    private String id;
    @NotNull(message = "bank cant be null")
	private List<String> bank;
    @NotEmpty
    @NotNull(message = "name cant be null")
	private String name;
    @NotNull(message = "clienttype cant be null")
	private String clienttype;
    @NotNull(message = "clientcode cant be null")
	private String clientcode;
  
}
