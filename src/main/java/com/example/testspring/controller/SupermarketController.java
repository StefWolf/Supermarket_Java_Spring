package com.example.testspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.testspring.model.Fornecedores;
import com.example.testspring.service.CategoriaService;
import com.example.testspring.service.FornecedorService;
import com.example.testspring.service.ProdutoService;

import org.springframework.ui.Model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Controller 
public class SupermarketController {
	
	@Autowired
	FornecedorService fornecedorService;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	CategoriaService categoriaService;
    
    @GetMapping("/home")
    public String supermarket(Model model) {
        
        model.addAttribute("amount_fornecedores", fornecedorService.findAllFornecedores().size());
        model.addAttribute("amount_produtos", produtoService.findAllProdutos().size());
        model.addAttribute("amount_categorias", categoriaService.findAllCategorias().size());
        return "home";
    } 
    
}
