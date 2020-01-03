package com.denarde.pedidosapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.denarde.pedidosapi.dto.BaseDTO;
import com.denarde.pedidosapi.dto.ProdutoDTO;
import com.denarde.pedidosapi.model.Produto;
import com.denarde.pedidosapi.service.ProdutoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/produtos")
@Api(value = "Produtos")
public class ProdutoController {

	@Autowired
	private ProdutoServiceImpl service;

	@GetMapping
	@ApiOperation(value = "Retorna todos os produtos")
	public ResponseEntity<List<Produto>> getProdutos() {
		List<Produto> produtos = service.getAll();
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna o produto por id")
	public ResponseEntity<BaseDTO> getProduto(@PathVariable("id") Integer id) {
		Optional<Produto> produto = service.getProduto(id);
		ProdutoDTO response = new ProdutoDTO();
		if (produto.isPresent()) {
			response = service.converteDTO(produto.get());
		} else {
			response.setMensagem("Produto não encontrado.");
			response.setId(id);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta o produto informado")
	public ResponseEntity<ProdutoDTO> deletar(@PathVariable("id") Integer id) {
		Optional<Produto> produto = service.getProduto(id);
		ProdutoDTO response = new ProdutoDTO();
		if (produto.isPresent()) {
			service.deletar(produto.get());
			response.setMensagem("Produto deletado com sucesso.");
			response.setId(id);
		} else {
			response.setMensagem("Produto não encontrado.");
			response.setId(id);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Salva um produto")
	public ResponseEntity<ProdutoDTO> salvar(@RequestBody ProdutoDTO produtoDTO) {
		Produto produto = service.salvar(service.converteProduto(produtoDTO));
		ProdutoDTO response = service.converteDTO(produto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza o produto informado")
	public ResponseEntity<ProdutoDTO> atualizar(@PathVariable("id") Integer id, @RequestBody Produto produtoAtualizar) {
		Optional<Produto> produto = service.getProduto(id);
		ProdutoDTO response = new ProdutoDTO();
		if (produto.isPresent()) {
			produtoAtualizar.setIdProduto(id);
			produtoAtualizar = service.salvar(produtoAtualizar);
			response = service.converteDTO(produtoAtualizar);
		} else {
			response.setMensagem("Produto não encontrado.");
			response.setId(id);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
