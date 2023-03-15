package com.example.testspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "preco")
	private Double preco;
	
	@ManyToOne
	@JoinColumn(name="id_fornecedor", nullable=false)
	private Fornecedores fornecedor;
	
	@ManyToOne
	@JoinColumn(name= "id_categoria", nullable=false)
	private ProdutosCategoria categoria;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public void setFornecedor(Fornecedores fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void setCategoria(ProdutosCategoria categoria) {
		this.categoria = categoria;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Double getPreco() {
		return this.preco;
	}
	
	public Fornecedores getFornecedor() {
		return this.fornecedor;
	}
	
	public ProdutosCategoria getCategoria() {
		return this.categoria;
	}
	
}
