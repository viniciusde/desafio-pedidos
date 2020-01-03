package com.denarde.pedidosapi.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDTO {
	
	private int idPedido;
	private int idProduto;
	private String produto;
	private BigDecimal quantidade;
	private BigDecimal valor;
	private BigDecimal subTotal;
}
