package com.alura.challenge.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alura.challenge.exception.NotFoundException;
import com.alura.challenge.model.Categoria;
import com.alura.challenge.model.Message;
import com.alura.challenge.model.Video;
import com.alura.challenge.repository.CategoriaRepository;
import com.alura.challenge.repository.VideoRepository;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceImplTest {

	@InjectMocks
	private CategoriaServiceImpl categoriaService;
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	@Mock
	private VideoRepository videoRepository;
	
	Optional<Categoria> optCategoria;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this); 
	    Categoria categoria = new Categoria();
	    categoria.setId(1);
	    categoria.setTitulo("Title");
	    categoria.setCor("cor");
	    optCategoria = Optional.of(categoria);
	}
	
	@Test
	void testGetCategoriaByIdExists() {	 
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = "Title";
		Categoria categoria2 = categoriaService.getCategoria(1);
		assertEquals(categoria2.getTitulo(), titulo, "Teste de igualdade");
	}
	
	@Test
	void testGetCategoriaByIdNotExists() {	 
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		Assertions.assertThrows(NotFoundException.class, () -> {
			categoriaService.getCategoria(1);
		});
	}
	
	@Test
	void testGetCategorias() {
		Categoria categoria_1 = new Categoria();
		Categoria categoria_2 = new Categoria();
		List<Categoria> listCategorias = new ArrayList<Categoria>();
		listCategorias.add(categoria_1);
		listCategorias.add(categoria_2);
		Iterable<Categoria> categorias = listCategorias;
		Mockito.when(categoriaRepository.findAll()).thenReturn(categorias);
		assertNotNull("Teste de Objeto Not Null", categoriaService.getCategorias());
	}
	
	@Test
	void testGetCategoriaVideos() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		Video video_1 = new Video();
		Video video_2 = new Video();
		List<Video> listVideos = new ArrayList<Video>();
		listVideos.add(video_1);
		listVideos.add(video_2);
		Iterable<Video> videos = listVideos;
		Mockito.when(videoRepository.findByCategoriaId(1)).thenReturn(videos);
		assertNotNull("Teste de Objeto Not Null", categoriaService.getCategoriaVideos(1));
	}
	
	@Test
	void testGetCategoriaVideosNotExists() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		Assertions.assertThrows(NotFoundException.class, () -> {
			categoriaService.getCategoriaVideos(1);
		});
	}
	
	@Test
	void testAddCategorias() {
		String titulo = "testeTitulo";
		String cor = "testeCor";
		Message m = categoriaService.addCategoria(titulo, cor);
		assertEquals(m.getMensagem(), "Categoria adicionada", "Teste de igualdade");
	}
	
	@Test
	void testAddCategoriasTituloNull() {
		String titulo = null;
		String cor = "testeCor";
		Message m = categoriaService.addCategoria(titulo, cor);
		assertEquals(m.getMensagem(), "O campo Titulo é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testAddCategoriasTituloEmpty() {
		String titulo = "";
		String cor = "testeCor";
		Message m = categoriaService.addCategoria(titulo, cor);
		assertEquals(m.getMensagem(), "O campo Titulo é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testAddCategoriasCorNull() {
		String titulo = "testeTitulo";
		String cor = null;
		Message m = categoriaService.addCategoria(titulo, cor);
		assertEquals(m.getMensagem(), "O campo Cor é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testAddCategoriasCorEmpty() {
		String titulo = "testeTitulo";
		String cor = "";
		Message m = categoriaService.addCategoria(titulo, cor);
		assertEquals(m.getMensagem(), "O campo Cor é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testUpdateCategorias() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = "testeTitulo";
		String cor = "testeCor";
		Message m = categoriaService.updateCategoria(1, titulo, cor);
		assertEquals(m.getMensagem(), "Categoria alterada", "Teste de igualdade");
	}
	
	@Test
	void testUpdateCategoriasNotExists() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		String titulo = "testeTitulo";
		String cor = "testeCor";
		Assertions.assertThrows(NotFoundException.class, () -> {
			categoriaService.updateCategoria(1, titulo, cor);
		});
	}
	
	@Test
	void testUpdateCategoriasTituloNull() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo =null;
		String cor = "testeCor";
		Message m = categoriaService.updateCategoria(1, titulo, cor);
		assertEquals(m.getMensagem(), "O campo Titulo é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testUpdateCategoriasCorNull() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = "testeTitulo";
		String cor =null;
		Message m = categoriaService.updateCategoria(1, titulo, cor);
		assertEquals(m.getMensagem(), "O campo Cor é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testDeleteCategorias() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		assertEquals(categoriaService.deleteCategoria(1), "Categoria deletada", "Teste de igualdade");
	}
	
	@Test
	void testDeleteVideosNotExists() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		Assertions.assertThrows(NotFoundException.class, () -> {
			categoriaService.deleteCategoria(1);
		});
	}
	

}
