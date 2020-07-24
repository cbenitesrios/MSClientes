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
public class UpdateClientRequest {

  private String id;
  private String bank;
  private String firstname;
  private String lastname;
  private String clienttype;
  private String clientcode;
  
}
