package com.everis.msclient.model.response;

import com.everis.msclient.model.dto.ClientDTO;

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
public class FindClientResponse {
	private ClientDTO clientresponse;
}
