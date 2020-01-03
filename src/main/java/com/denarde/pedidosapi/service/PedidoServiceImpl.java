package com.denarde.pedidosapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denarde.pedidosapi.dto.ItemPedidoDTO;
import com.denarde.pedidosapi.dto.PedidoDTO;
import com.denarde.pedidosapi.dto.PedidoDTOResponse;
import com.denarde.pedidosapi.model.Item;
import com.denarde.pedidosapi.model.Pedido;
import com.denarde.pedidosapi.repository.PedidoRepository;

@Service
public class PedidoServiceImpl {

	private static final String STATUS_ABERTO = "ABERTO";
	private static final String STATUS_FECHADO = "FECHADO";

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ItemServiceImpl itemService;
	
	@Autowired
	private ProdutoServiceImpl produtoService;
	
	@Autowired
	private ClienteServiceImpl clienteService;

	public Pedido salvar(Pedido pedido) {
		return repository.save(pedido);
	}
	
	public Pedido fecharPedido(int idPedido) throws Exception {
		Optional<Pedido> pedido = getPedido(idPedido);
		if(pedido.isPresent()) {
			pedido.get().setStatus(STATUS_FECHADO);
			salvar(pedido.get());
		} else {
			throw new Exception("Pedido não encontrado");
		}
		
		return pedido.get();
	}
	
	public Item convertItem(ItemPedidoDTO dto) {
		Item item = new Item();
		item.setPedido((getPedido(dto.getIdPedido()).get()));
		item.setQuantidade(dto.getQuantidade());
		item.setProduto((produtoService.getProduto(dto.getIdProduto())).get());
		//se o valor nao for nulo e nao for negativo, recebe valor atualizado / senao pega o preço original do produto.
		if(dto.getValor() != null && dto.getValor().compareTo(BigDecimal.ZERO) > 0) {
			item.setValor(dto.getValor());
		} else {
			item.setValor(item.getProduto().getPreco());
		}
		item.setSubTotal(dto.getQuantidade().multiply(item.getValor()));
		return item;
	}
	
	public ItemPedidoDTO convertItemDTO(Item item) {
		ItemPedidoDTO dto = new ItemPedidoDTO();
		dto.setIdPedido(item.getPedido().getIdPedido());
		dto.setIdProduto(dto.getIdProduto());
		dto.setProduto(item.getProduto().getDescricao());
		dto.setQuantidade(item.getQuantidade());
		dto.setSubTotal(item.getSubTotal());
		dto.setValor(item.getValor());
		return dto;
	}
	
	public Item addItem(Item item) {
		return itemService.salvar(item);
	}
	
	
	public void removeItem(Item item) {
		itemService.deletar(item);
	}
	

	public Optional<Pedido> getPedido(Integer id) {
		return repository.findById(id);
	}

	public Pedido convertePedido(PedidoDTO dto) {
		Pedido pedido = new Pedido();
		pedido.setCliente((clienteService.getCliente(dto.getCliente())).get());
		pedido.setData(LocalDate.now());
		pedido.setSessao(dto.getSessao());
		pedido.setStatus(STATUS_ABERTO);
		return pedido;
	}
	
	public PedidoDTOResponse converteDTO (Pedido pedido) {
		PedidoDTOResponse response = new PedidoDTOResponse();
		response.setCliente(clienteService.converteDTO(pedido.getCliente()));
		response.setData(pedido.getData());
		response.setSessao(pedido.getSessao());
		response.setIdPedido(pedido.getIdPedido());
		response.setItens(new ArrayList<>());
		response.setStatus(pedido.getStatus());
		if(pedido.getItens() != null && pedido.getItens().size() > 0) {
			pedido.getItens().forEach(item-> response.getItens().add(convertItemDTO(item)));
		} 
		
		return response;
	}

}
