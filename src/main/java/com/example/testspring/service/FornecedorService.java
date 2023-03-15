package com.example.testspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testspring.model.Fornecedores;

import com.example.testspring.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public void salvarFornecedor(Fornecedores fornecedor) {
		fornecedorRepository.save(fornecedor);
	}
	
	public List<Fornecedores> findAllFornecedores(){
		return fornecedorRepository.findAll();
	}
	
	public void deletarFornecedor(Fornecedores fornecedor) {
		fornecedorRepository.delete(fornecedor);
	}
	
	public Optional<Fornecedores> findFornecedorById(Integer id) {
		return fornecedorRepository.findById(id);
	}
	
	public Boolean existById(Integer id) {
		return fornecedorRepository.existsById(id);
	}
	
	public void atualizarFornecedor(Integer id, Fornecedores fornecedor) {
		fornecedor.setId(id);
		fornecedorRepository.save(fornecedor);
	}
}
