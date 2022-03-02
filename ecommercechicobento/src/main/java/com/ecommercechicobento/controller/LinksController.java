package com.ecommercechicobento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LinksController {
    
    @RequestMapping(value={"/"})
    public String index(){
        return "index";
    }

    @RequestMapping(value="/cadastrarCliente", method = RequestMethod.GET)
    public String form(){
        return "cliente/cadastrarCliente";
    }
}

