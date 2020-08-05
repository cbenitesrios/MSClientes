package com.everis.msclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import org.springframework.data.annotation.Id; 
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
  private String clientcode;
  private String name;
  private String bankassociate;
  private String clienttype; 
  private String clientypedesc; 
}
