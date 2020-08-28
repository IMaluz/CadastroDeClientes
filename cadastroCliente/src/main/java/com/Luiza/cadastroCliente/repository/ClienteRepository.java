package com.Luiza.cadastroCliente.repository;

import org.springframework.data.repository.CrudRepository;

import com.Luiza.cadastroCliente.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, String>{
	Cliente findByCodigo(long codigo);
	
}
