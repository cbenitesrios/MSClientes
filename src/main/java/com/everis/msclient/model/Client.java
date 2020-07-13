package com.everis.msclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data 
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
@ToString 
public class Client {  
  @Id
  private String id;
  @Indexed(name = "clientcode", unique = true)
  private String clientcode;
  private String firstname;
  private String lastname;
  private String clienttype; 
}
