package com.denarde.pedidosapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denarde.pedidosapi.dto.ItemPedidoDTO;
import com.denarde.pedidosapi.dto.PedidoDTO;
import com.denarde.pedidosapi.dto.PedidoDTOResponse;
import com.denarde.pedidosapi.model.Item;
import com.denarde.pedidosapi.model.Pedido;
import com.denarde.pedidosapi.service.ItemServiceImpl;
import com.denarde.pedidosapi.service.PedidoServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoServiceImpl service;
	
	@Autowired
	private ItemServiceImpl itemService;
	
	@PostMapping("/novo")
	@ApiOperation(value = "Criar um novo pedido")
	public ResponseEntity<PedidoDTOResponse> iniciarPedido(@RequestBody PedidoDTO pedidoDTO) {
		Pedido pedido = service.salvar(service.convertePedido(pedidoDTO));
		PedidoDTOResponse response = service.converteDTO(pedido);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/addItem")
	@ApiOperation(value = "Adicionar item")
	public ResponseEntity<PedidoDTOResponse> addItem(@RequestBody ItemPedidoDTO itemDTO) {
		Item item = service.addItem(service.convertItem(itemDTO));
		PedidoDTOResponse response = new PedidoDTOResponse();
		response = service.converteDTO((service.getPedido(itemDTO.getIdPedido())).get());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/removeItem/{idPedido}/{idItem}")
	@ApiOperation(value = "Remover item")
	public ResponseEntity<PedidoDTOResponse> removeItem(@PathVariable("idPedido") int idPedido, @PathVariable("idItem") int idItem) {
		Optional<Pedido> pedido = service.getPedido(idPedido);
		Optional<Item> item = itemService.getItemPorPedidoEPorItem(idItem, pedido.get());
		service.removeItem(item.get());
		PedidoDTOResponse response = service.converteDTO(pedido.get());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@PatchMapping("/fecharPedido/{idPedido}")
	@ApiOperation(value = "Fechar pedido")
	public ResponseEntity<PedidoDTOResponse> fecharPedido(@PathVariable("idPedido") int idPedido) throws Exception {
		Pedido pedido = service.fecharPedido(idPedido);
		PedidoDTOResponse response = service.converteDTO(pedido);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
