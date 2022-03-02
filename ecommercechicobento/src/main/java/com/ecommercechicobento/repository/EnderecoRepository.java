package com.ecommercechicobento.repository;

import com.ecommercechicobento.model.Cliente;
import com.ecommercechicobento.model.Endereco;

import org.springframework.data.repository.CrudRepository;

public interface EnderecoRepository extends CrudRepository<Endereco, String>{
    Iterable<Endereco> findByCliente(Cliente cliente); 
}
