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

import com.example.testspring.model.Produto;
import com.example.testspring.model.ProdutosCategoria;
import com.example.testspring.service.CategoriaService;
import com.example.testspring.service.FornecedorService;
import com.example.testspring.service.ProdutoService;

@Controller
@RequestMapping("produto")
public class ProdutoController {
	
	@Value("produto")
	private String path;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("produtos", produtoService.findAllProdutos());
		model.addAttribute("fornecedores", fornecedorService.findAllFornecedores());
		model.addAttribute("categorias", categoriaService.findAllCategorias());
		model.addAttribute("produto", new Produto());
		return path+"/listar";
	}
	
	@PostMapping(path = "/salvar")
	public ModelAndView salvar(Produto produto, RedirectAttributes attributes) {
		
		ModelAndView model = new ModelAndView(new RedirectView("listar", true));
		try {
			produtoService.salvarProduto(produto);
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
			Optional<Produto> produto = produtoService.findProdutoById(id);
			if(produto != null)
				produtoService.deletarProduto(produto.get());
			
			System.out.println("Deletado com sucesso");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return model;
	}
	
	@GetMapping(path = "editar/{id}")
	 public String editar(@PathVariable(name = "id") Integer id, Model model) {
		
		Optional<Produto> produto = produtoService.findProdutoById(id);
		model.addAttribute("produto", produto);
		model.addAttribute("fornecedores", fornecedorService.findAllFornecedores());
		model.addAttribute("categorias", categoriaService.findAllCategorias());
		return path+"/editar";
	}
	
	@PostMapping(path = "/editar/salvar/{id}")
	 public ModelAndView editar(@PathVariable (name = "id") Integer id, Produto produto) {
	   	ModelAndView model = new ModelAndView(new RedirectView("/"+path+"/listar", false));
	   	
	   	try {
	   		
	   		produtoService.atualizarProduto(id, produto);
	   		System.out.println("Editado com sucesso");
	   		
	   	} catch(Exception e) {
	   		
	   		System.out.println(e.getMessage());
	   	}
	   	
	   	return model;
	  }
	
}
