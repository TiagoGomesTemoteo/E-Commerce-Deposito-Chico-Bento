package com.ecommercechicobento.repository;

import com.ecommercechicobento.model.Cliente;

import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository <Cliente, String>{
    Cliente findByCodigo (int codigo);   
}
