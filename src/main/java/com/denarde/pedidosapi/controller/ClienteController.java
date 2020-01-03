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
import com.denarde.pedidosapi.dto.ClienteDTO;
import com.denarde.pedidosapi.dto.ClientesDTO;
import com.denarde.pedidosapi.model.Cliente;
import com.denarde.pedidosapi.service.ClienteServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/clientes")
@Api(value = "Clientes")
public class ClienteController {

	@Autowired
	private ClienteServiceImpl service;

	@GetMapping
	@ApiOperation(value = "Retorna todos os clientes")
	public ResponseEntity<ClientesDTO> getCategorias() {
		List<Cliente> clientes = service.getAll();
		ClientesDTO response = new ClientesDTO();
		if(clientes != null && clientes.size() > 0) {
			clientes.forEach(cliente-> response.getClientes().add(service.converteDTO(cliente)));
		} else {
			response.setMensagem("Nenhum cliente cadastrado.");
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna o cliente por id")
	public ResponseEntity<BaseDTO> getCliente(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente = service.getCliente(id);
		ClienteDTO response = new ClienteDTO();
		if (cliente.isPresent()) {
			response = service.converteDTO(cliente.get());
		} else {
			response.setMensagem("Cliente não encontrado.");
			response.setId(id);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta o cliente informado")
	public ResponseEntity<ClienteDTO> deletar(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente = service.getCliente(id);
		ClienteDTO response = new ClienteDTO();
		if (cliente.isPresent()) {
			service.deletar(cliente.get());
			response.setMensagem("Cliente deletado com sucesso.");
			response.setId(id);
		} else {
			response.setMensagem("Cliente não encontrado.");
			response.setId(id);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = "Salva um cliente")
	public ResponseEntity<ClienteDTO> salvar(@RequestBody Cliente cliente) {
		cliente = service.salvar(cliente);
		ClienteDTO response = service.converteDTO(cliente);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza o cliente informado")
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable("id") Integer id,
			@RequestBody Cliente clienteAtualizar) {
		Optional<Cliente> cliente = service.getCliente(id);
		ClienteDTO response = new ClienteDTO();
		if (cliente.isPresent()) {
			clienteAtualizar.setIdCliente(id);
			clienteAtualizar = service.salvar(clienteAtualizar);
			response = service.converteDTO(clienteAtualizar);
		} else {
			response.setMensagem("Cliente não encontrado.");
			response.setId(id);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
