package com.denarde.pedidosapi.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPedido")
	private int idPedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({ @JoinColumn(name = "idCliente", referencedColumnName = "idCliente") })
	private Cliente cliente;
	
	@Column
	private LocalDate data;
	
	@Column
	private String status;
	
	@Column
	private String sessao;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pedido", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Item> itens;
	
}
