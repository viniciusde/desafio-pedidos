package com.denarde.pedidosapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.denarde.pedidosapi.model.Item;
import com.denarde.pedidosapi.model.Pedido;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	@Query("select o from Item o where o.pedido = :pedido")
	List<Item> findByPedido(@Param("pedido") Pedido pedido);
	
	Optional<Item> findByIdItemAndPedido(int idItem, Pedido pedido);

}
