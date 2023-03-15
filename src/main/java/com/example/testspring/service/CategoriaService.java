package com.example.testspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testspring.model.ProdutosCategoria;
import com.example.testspring.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public void salvarCategoria(ProdutosCategoria categoria) {
		categoriaRepository.save(categoria);
	}
	
	public List<ProdutosCategoria> findAllCategorias(){
		return categoriaRepository.findAll();
	}
	
	public void deletarCategoria(ProdutosCategoria categoria) {
		categoriaRepository.delete(categoria);
	}
	
	public Optional<ProdutosCategoria> findCategoriaById(Integer id) {
		return categoriaRepository.findById(id);
	}
	
	public Boolean existById(Integer id) {
		return categoriaRepository.existsById(id);
	}
	
	public void atualizarCategoria(Integer id, ProdutosCategoria categoria) {
		categoria.setId(id);
		categoriaRepository.save(categoria);
	}
}
