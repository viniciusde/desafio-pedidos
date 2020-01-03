package com.denarde.pedidosapi.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO extends BaseDTO {
	
	private int id;
	private int categoria;
	private String produto;
	private BigDecimal preco;
	private int quantidade;
	private String descricao;
	private String foto;

}
