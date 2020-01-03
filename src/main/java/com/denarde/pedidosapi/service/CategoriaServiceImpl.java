package com.denarde.pedidosapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denarde.pedidosapi.model.Categoria;
import com.denarde.pedidosapi.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria salvar(Categoria categoria) {
		return repository.save(categoria);
	}
	
	public void deletar(Categoria categoria) {
		repository.delete(categoria);
	}
	
	public Optional<Categoria> getCategoria(Integer id) {
		return repository.findById(id);
	}
	
	public List<Categoria> getAll(){
		return repository.findAll();
	}
	
}
