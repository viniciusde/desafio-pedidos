package com.denarde.pedidosapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denarde.pedidosapi.dto.ClienteDTO;
import com.denarde.pedidosapi.dto.EnderecoDTO;
import com.denarde.pedidosapi.model.Cliente;
import com.denarde.pedidosapi.repository.ClienteRepository;

@Service
public class ClienteServiceImpl {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente salvar(Cliente cliente) {
		return repository.save(cliente);
	}
	
	public void deletar(Cliente cliente) {
		repository.delete(cliente);
	}
	
	public Optional<Cliente> getCliente(Integer id) {
		return repository.findById(id);
	}
	
	public List<Cliente> getAll(){
		return repository.findAll();
	}
	
	
	public ClienteDTO converteDTO(Cliente cliente) {
		ClienteDTO response = new ClienteDTO();
		response.setId(cliente.getIdCliente());
		response.setNome(cliente.getNome());
		response.setEmail(cliente.getEmail());
		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setBairro(cliente.getBairro());
		endereco.setCep(cliente.getCep());
		endereco.setCidade(cliente.getCidade());
		endereco.setEstado(cliente.getEstado());
		endereco.setRua(cliente.getRua());
		response.setEndereco(endereco);
		return response;
	}
}
