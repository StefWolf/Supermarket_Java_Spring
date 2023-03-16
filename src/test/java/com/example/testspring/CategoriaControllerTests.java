package com.example.testspring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.testspring.model.ProdutosCategoria;
import com.example.testspring.service.CategoriaService;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Value("categoria")
	private String path;
	
	@MockBean
	CategoriaService categoriaService;
	
	public ProdutosCategoria novaCategoria() {
		ProdutosCategoria categoria = new ProdutosCategoria();
		categoria.setNome("categoria teste");
		categoria.setId(1);
		
		return categoria;
	}
	
	@Test
	void listarTest() throws Exception {
		
		List<ProdutosCategoria> categorias = new ArrayList<>();
		categorias.add(novaCategoria());
		
		Mockito.when(categoriaService.findAllCategorias()).thenReturn(categorias);
		this.mockMvc.perform(get("/"+path+"/listar")).andDo(print())
											.andExpect(status().isOk());
	}
	
}
