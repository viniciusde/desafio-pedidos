package com.denarde.pedidosapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denarde.pedidosapi.model.Item;
import com.denarde.pedidosapi.model.Pedido;
import com.denarde.pedidosapi.repository.ItemRepository;

@Service
public class ItemServiceImpl {

	@Autowired
	private ItemRepository repository;
	
	public Item salvar(Item item) {
		return repository.save(item);
	}
	
	public void deletar(Item item) {
		repository.delete(item);
	}

	public Optional<Item> getItem(Integer id) {
		return repository.findById(id);
	}
	
	public List<Item> getItemPorPedido(Pedido pedido) {
		return repository.findByPedido(pedido);
	}
	
	public Optional<Item> getItemPorPedidoEPorItem(int idItem, Pedido pedido) {
		return repository.findByIdItemAndPedido(idItem, pedido);
	}
}
