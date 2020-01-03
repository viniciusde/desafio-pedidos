package com.denarde.pedidosapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denarde.pedidosapi.dto.ProdutoDTO;
import com.denarde.pedidosapi.model.Produto;
import com.denarde.pedidosapi.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaServiceImpl categoriaService;
	
	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}
	
	public void deletar(Produto produto) {
		repository.delete(produto);
	}
	
	public Optional<Produto> getProduto(Integer id) {
		return repository.findById(id);
	}
	
	public List<Produto> getAll(){
		return repository.findAll();
	}
	
	public ProdutoDTO converteDTO(Produto produto) {
		ProdutoDTO response = new ProdutoDTO();
		response.setId(produto.getIdProduto());
		response.setCategoria(produto.getCategoria().getIdCategoria());
		response.setDescricao(produto.getDescricao());
		response.setFoto(produto.getFoto());
		response.setPreco(produto.getPreco());
		response.setProduto(produto.getProduto());
		response.setQuantidade(produto.getQuantidade());
		return response;
	}
	
	public Produto converteProduto(ProdutoDTO dto) {
		Produto produto = new Produto();
		produto.setIdProduto(dto.getId());
		produto.setCategoria(categoriaService.getCategoria(dto.getCategoria()).get());
		produto.setDescricao(dto.getDescricao());
		produto.setFoto(dto.getFoto());
		produto.setPreco(dto.getPreco());
		produto.setProduto(dto.getProduto());
		produto.setQuantidade(dto.getQuantidade());
		return produto;
	}
	
}
