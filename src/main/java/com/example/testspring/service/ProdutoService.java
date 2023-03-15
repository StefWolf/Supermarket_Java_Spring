package com.example.testspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testspring.model.Produto;
import com.example.testspring.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public void salvarProduto(Produto produto) {
		produtoRepository.save(produto);
	}
	
	public List<Produto> findAllProdutos(){
		return produtoRepository.findAll();
	}
	
	public void deletarProduto(Produto produto) {
		produtoRepository.delete(produto);
	}
	
	public Optional<Produto> findProdutoById(Integer id) {
		return produtoRepository.findById(id);
	}
	
	public Boolean existById(Integer id) {
		return produtoRepository.existsById(id);
	}
	
	public void atualizarProduto(Integer id, Produto produto) {
		produto.setId(id);
		produtoRepository.save(produto);
	}
}
