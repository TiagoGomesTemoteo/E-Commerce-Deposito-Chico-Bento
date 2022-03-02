package com.ecommercechicobento.repository;

import com.ecommercechicobento.model.Cartao;
import com.ecommercechicobento.model.Cliente;

import org.springframework.data.repository.CrudRepository;

public interface CartaoRepository extends CrudRepository<Cartao, String>{
    Iterable<Cartao> findByCliente(Cliente cliente);   
}
