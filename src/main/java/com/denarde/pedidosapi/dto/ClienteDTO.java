package com.denarde.pedidosapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO extends BaseDTO {
	
	private int id;
	private String nome;
	private String email;
	private EnderecoDTO endereco;
}
