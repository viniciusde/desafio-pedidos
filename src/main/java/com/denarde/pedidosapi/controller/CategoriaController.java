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
import com.denarde.pedidosapi.dto.CategoriaDTO;
import com.denarde.pedidosapi.model.Categoria;
import com.denarde.pedidosapi.service.CategoriaServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/categorias")
@Api(value = "Categorias")
public class CategoriaController {

	@Autowired
	private CategoriaServiceImpl service;

	@GetMapping
	public ResponseEntity<List<Categoria>> getCategorias() {
		List<Categoria> categorias = service.getAll();
		return new ResponseEntity<>(categorias, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna a categoria por id")
	public ResponseEntity<BaseDTO> getCategoria(@PathVariable("id") Integer id) {
		Optional<Categoria> categoria = service.getCategoria(id);
		CategoriaDTO response = new CategoriaDTO();
		if (categoria.isPresent()) {
			response.setCategoria((categoria.get()).getCategoria());
			response.setId((categoria.get()).getIdCategoria());
		} else {
			response.setMensagem("Categoria não encontrada.");
			response.setId(id);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta a categoria informada")
	public ResponseEntity<CategoriaDTO> deletar(@PathVariable("id") Integer id) {
		Optional<Categoria> categoria = service.getCategoria(id);
		CategoriaDTO response = new CategoriaDTO();
		if (categoria.isPresent()) {
			service.deletar(categoria.get());
			response.setMensagem("Categoria deletada com sucesso.");
			response.setId(id);
		} else {
			response.setMensagem("Categoria não encontrada.");
			response.setId(id);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Salva uma categoria")
	public ResponseEntity<CategoriaDTO> salvar(@RequestBody Categoria categoria) {
		categoria = service.salvar(categoria);
		CategoriaDTO response = new CategoriaDTO();
		response.setId(categoria.getIdCategoria());
		response.setCategoria(categoria.getCategoria());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza a categoria informada")
	public ResponseEntity<CategoriaDTO> atualizar(@PathVariable("id") Integer id,
			@RequestBody Categoria categoriaAtualizar) {
		Optional<Categoria> categoria = service.getCategoria(id);
		CategoriaDTO response = new CategoriaDTO();
		if (categoria.isPresent()) {
			categoriaAtualizar.setIdCategoria(id);
			categoriaAtualizar = service.salvar(categoriaAtualizar);
			response.setId(id);
			response.setCategoria(categoriaAtualizar.getCategoria());
		} else {
			response.setMensagem("Categoria não encontrada.");
			response.setId(id);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
