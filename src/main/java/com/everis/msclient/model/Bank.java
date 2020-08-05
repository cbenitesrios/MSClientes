package com.everis.msclient.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
@ToString 
public class Bank { 
	@Id
	private String id;
	private String code;
	private String name;	

}
