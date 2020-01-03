package com.denarde.pedidosapi.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDTOResponse extends BaseDTO {
	
	private int idPedido;
	private String sessao;
	private String status;
	private LocalDate data;
	private ClienteDTO cliente;
	private List<ItemPedidoDTO> itens;
	

}
