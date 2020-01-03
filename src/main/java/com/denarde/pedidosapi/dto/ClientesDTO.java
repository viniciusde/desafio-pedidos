package com.denarde.pedidosapi.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientesDTO extends BaseDTO{
	
	private List<ClienteDTO> clientes = new ArrayList<>();

}
