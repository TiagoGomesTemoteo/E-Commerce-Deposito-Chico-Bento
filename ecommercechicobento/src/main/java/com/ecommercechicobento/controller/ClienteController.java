package com.ecommercechicobento.controller;

import javax.validation.Valid;

import com.ecommercechicobento.model.*;
import com.ecommercechicobento.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClienteController {
    
    @Autowired
    ClienteRepository cr;

    @Autowired
    CartaoRepository cartaoR;

    @Autowired
    EnderecoRepository enderecoR;

    @RequestMapping(value="/cadastrarEndereco", method = RequestMethod.GET)
    public ModelAndView cadastrarEndereco(int codigo){
        ModelAndView mv = new ModelAndView("cliente/cadastrarEndereco");
        Cliente cliente = cr.findByCodigo(codigo);
        mv.addObject("cliente", cliente);
        return mv;
    }

    /*Cadastrar um cliente*/
    @RequestMapping("/cadastroCliente")
    public String formPost(@Valid Cliente cliente,
    BindingResult result, RedirectAttributes attributes){
        
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem","Verifique os campos!");
            return "redirect:/cadastrarCliente";
        }

        cr.save(cliente);
        attributes.addFlashAttribute("mensagem","Cliente cadastrado com Sucesso!!!");
        return "redirect:/cadastrarCliente";
    }

    /*Alterar cliente */
    @RequestMapping(value="/alterarCliente/{codigo}", method = RequestMethod.POST)
    public String formPut(@PathVariable("codigo") int codigo, @Valid Cliente clienteAtualizado,
    BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem","Verifique os campos!");
            return "redirect:/{codigo}";
        }

        Cliente clienteAntigo = cr.findByCodigo(codigo);

        clienteAntigo.setNome(clienteAtualizado.getNome());
        clienteAntigo.setDataNascimento(clienteAtualizado.getDataNascimento());
        clienteAntigo.setGenero(clienteAtualizado.getGenero());
        clienteAntigo.setCpf(clienteAtualizado.getCpf());
        clienteAntigo.setTelefone(clienteAtualizado.getTelefone());
        clienteAntigo.setEmail(clienteAtualizado.getEmail());

        cr.save(clienteAntigo);
        attributes.addFlashAttribute("mensagem","Cliente atualizado com Sucesso!!!");
        return "redirect:/{codigo}";
    }

    /*Listar todos os clientes*/
    @RequestMapping("/listarClientes")
    public ModelAndView listarClientes() {
        ModelAndView mv = new ModelAndView("cliente/listarCliente");
        Iterable<Cliente> clientes = cr.findAll();
        mv.addObject("clientes", clientes);
        return mv;
    }

    /*Detalhes do Cliente*/ 
    @RequestMapping(value="/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesCliente(@PathVariable("codigo") int codigo){
        Cliente cliente = cr.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("cliente/detalhesCliente");
        mv.addObject("cliente", cliente);
        return mv;
    }

    @RequestMapping("/gerenciarEnderecos")
    public ModelAndView gerenciarEnderecos(int codigo){
        Cliente cliente = cr.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("cliente/gerenciarEndereco");
        Iterable<Endereco> enderecos = enderecoR.findByCliente(cliente);
        mv.addObject("enderecos", enderecos);
        mv.addObject("cliente", cliente);
        return mv;
    }

    @RequestMapping(value="/cadastrarEndereco/cliente/{codigo}", method = RequestMethod.POST)
    public String formPost(@PathVariable("codigo") int codigo, @Valid Endereco endereco,
    BindingResult result, RedirectAttributes attributes){
        
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem","Verifique os campos!");
            return "redirect:/cadastrarEndereco";
        }
        Cliente cliente = cr.findByCodigo(codigo);
        endereco.setCliente(cliente);
        enderecoR.save(endereco);
        attributes.addFlashAttribute("mensagem","Endereço adicionado com Sucesso!!!");
        return "redirect:/listarClientes";
    }
    
}
