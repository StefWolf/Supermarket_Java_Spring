package com.example.testspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.testspring.model.ProdutosCategoria;

@Repository
public interface CategoriaRepository extends JpaRepository<ProdutosCategoria, Integer>{

}
