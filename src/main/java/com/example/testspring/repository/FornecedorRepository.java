package com.example.testspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.testspring.model.Fornecedores;

import jakarta.transaction.Transactional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedores, Integer> {

}
