package com.Luiza.cadastroCliente.repository;

import org.springframework.data.repository.CrudRepository;

import com.Luiza.cadastroCliente.models.Cliente;
import com.Luiza.cadastroCliente.models.Contatos;

public interface ContatosRepository extends CrudRepository<Contatos, String>{
	Iterable<Contatos> findByCliente(Cliente cliente);
	Contatos findByRg(String rg);
}
