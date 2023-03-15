package com.example.testspring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.testspring.model.ProdutosCategoria;
import com.example.testspring.service.CategoriaService;

@Controller
@RequestMapping("categoria")
public class CategoriaController {

	@Value("categoria")
	private String path;
	
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("categorias", categoriaService.findAllCategorias());
		model.addAttribute("categoria", new ProdutosCategoria());
		return path+"/listar";
	}
	
	@PostMapping(path = "/salvar")
	public ModelAndView salvar(ProdutosCategoria categoria, RedirectAttributes attributes) {
		
		ModelAndView model = new ModelAndView(new RedirectView("listar", true));
		try {
			categoriaService.salvarCategoria(categoria);
			attributes.addFlashAttribute("mensagem", "Salvo com sucesso");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return model;
	}
	
	@GetMapping(path="deletar/{id}")
	public ModelAndView deletar(@PathVariable(name = "id") Integer id) {
		
		ModelAndView model = new ModelAndView(new RedirectView("/"+path+"/listar", false));
		
		try {
			Optional<ProdutosCategoria> categoria = categoriaService.findCategoriaById(id);
			if(categoria != null)
				categoriaService.deletarCategoria(categoria.get());
			
			System.out.println("Deletado com sucesso");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return model;
	}
	
	@GetMapping(path = "editar/{id}")
	 public String editar(@PathVariable(name = "id") Integer id, Model model) {
		
		Optional<ProdutosCategoria> categoria = categoriaService.findCategoriaById(id);
		model.addAttribute("categoria", categoria);
		return path+"/editar";
	}
	
	@PostMapping(path = "/editar/salvar/{id}")
	 public ModelAndView editar(@PathVariable (name = "id") Integer id, ProdutosCategoria categoria) {
    	ModelAndView model = new ModelAndView(new RedirectView("/"+path+"/listar", false));
    	
    	try {
    		
    		categoriaService.atualizarCategoria(id, categoria);
    		System.out.println("Editado com sucesso");
    		
    	} catch(Exception e) {
    		
    		System.out.println(e.getMessage());
    	}
    	
    	return model;
    }
}
