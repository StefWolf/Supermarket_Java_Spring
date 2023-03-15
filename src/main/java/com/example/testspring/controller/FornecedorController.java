package com.example.testspring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.testspring.model.Fornecedores;
import com.example.testspring.repository.FornecedorRepository;
import com.example.testspring.service.FornecedorService;

@Controller
@RequestMapping("fornecedor")
public class FornecedorController {
	
	@Value("fornecedor")
	private String path;
	
	
	@Autowired
	FornecedorService fornecedorService;
	
	@Autowired
	FornecedorRepository fornecedorRepository;
	
	@GetMapping("/listar")
    public String listar(Model model) {
		
        model.addAttribute("fornecedores", fornecedorService.findAllFornecedores());
        model.addAttribute("fornecedor", new Fornecedores());
        return path+"/listar";
    }
	
	@PostMapping(path = "/salvar")
    public ModelAndView salvarFornecedor(Fornecedores fornecedor, RedirectAttributes attributes) {
		
    	ModelAndView model = new ModelAndView(new RedirectView("listar", true));
    	try {
    		fornecedorService.salvarFornecedor(fornecedor);
    		attributes.addFlashAttribute("mensagem", "Salvo com sucesso");
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	return model;
    }
    
    @GetMapping(path="deletar/{id}")
    public ModelAndView deletarFornecedor(@PathVariable(name = "id") Integer id) {
    	
    	ModelAndView model = new ModelAndView(new RedirectView("/"+path+"/listar", false));
    	try {
    		Optional<Fornecedores> fornecedor = fornecedorService.findFornecedorById(id);
    		if(fornecedor != null) 
    			fornecedorService.deletarFornecedor(fornecedor.get());
   
    		System.out.println("Deletado com sucesso");
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	return model;
    }
    
    @GetMapping(path = "editar/{id}")
    public String editar(@PathVariable(name = "id") Integer id, Model model) {
    	
    	Optional<Fornecedores> fornecedor = fornecedorService.findFornecedorById(id);
    	model.addAttribute("fornecedor", fornecedor);
    	return path+"/editar";
    }
    
    @PostMapping(path = "/editar/salvar/{id}")
    public ModelAndView editar(@PathVariable (name = "id") Integer id, Fornecedores fornecedor) {
    	ModelAndView model = new ModelAndView(new RedirectView("/"+path+"/listar", false));
    	
    	try {
    		
    		fornecedorService.atualizarFornecedor(id, fornecedor);
    		System.out.println("Editado com sucesso");
    		
    	} catch(Exception e) {
    		
    		System.out.println(e.getMessage());
    	}
    	
    	return model;
    }
}
