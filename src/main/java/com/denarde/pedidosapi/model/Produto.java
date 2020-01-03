package com.denarde.pedidosapi.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idProduto")
	private int idProduto;
	
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "idCategoria", referencedColumnName ="idCategoria") })
	private Categoria categoria;
	
	@NotEmpty
	@Column
	private String produto;
	
	@Column(name="preco")
	private BigDecimal preco;

	@Column
	private int quantidade;

	@Column
	private String descricao;
	
	@Column
	private String foto;

}
