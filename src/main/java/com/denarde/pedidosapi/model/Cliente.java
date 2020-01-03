package com.denarde.pedidosapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCliente")
	private int idCliente;
	
	@ApiModelProperty(value = "Nome da pessoa")
	@NotEmpty
	@Column(length = 200)
	private String nome;
	
	@NotEmpty
	@Column(length = 100)
	private String email;
	
	@NotEmpty
	@Column
	private String senha;
	
	@Column(length = 200)
	private String rua;
	
	@Column(length = 100)
	private String cidade;

	@Column(length = 100)
	private String bairro;
	
	@Column(length = 8)
	private String cep;

	@Column(length = 20)
	private String estado;
	
}
