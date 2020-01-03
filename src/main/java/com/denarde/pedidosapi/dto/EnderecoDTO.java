package com.denarde.pedidosapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
	
	private String rua;
	private String cidade;
	private String bairro;
	private String cep;
	private String estado;

}
