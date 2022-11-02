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
class VideoServiceImplTest {

	@InjectMocks
	private VideoServiceImpl videoService;
		
	@Mock
	private VideoRepository videoRepository;
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	Optional<Video> optVideo;
	Optional<Categoria> optCategoria;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this); 
		Video video = new Video();
	    video.setTitulo("Title");
	    optVideo = Optional.of(video);
	    Categoria categoria = new Categoria();
	    categoria.setId(1);
	    categoria.setTitulo("Title");
	    categoria.setCor("cor");
	    optCategoria = Optional.of(categoria);
	}
	
	@Test
	void testGetVideoByIdExists() {	 
		Mockito.when(videoRepository.findById(Mockito.any())).thenReturn(optVideo);
		String titulo = "Title";
		Video video2 = videoService.getVideo(1);
		assertEquals(video2.getTitulo(), titulo, "Teste de igualdade");
	}
	
	@Test
	void testGetVideoByIdNotExists() {	 
		Mockito.when(videoRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		Assertions.assertThrows(NotFoundException.class, () -> {
			videoService.getVideo(1);
		});
	}
	
	@Test
	void testAddVideos() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = "testeTitulo";
		String descricao = "testeDescricao";
		String url = "testeUrl";
		Message m = videoService.addVideo(1, titulo, descricao, url);
		assertEquals(m.getMensagem(), "Video adicionado", "Teste de igualdade");
	}
	
	@Test
	void testAddVideosWithoutCategoria() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(Optional.empty()).thenReturn(optCategoria);
		String titulo = "testeTitulo";
		String descricao = "testeDescricao";
		String url = "testeUrl";
		Message m = videoService.addVideo(null, titulo, descricao, url);
		assertEquals(m.getMensagem(), "Video adicionado", "Teste de igualdade");
	}
	
	@Test
	void testAddVideosTituloNull() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = null;
		String descricao = "testeDescricao";
		String url = "testeUrl";
		Message m = videoService.addVideo(1, titulo, descricao, url);
		assertEquals(m.getMensagem(), "O campo Titulo é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testAddVideosDescricaoNull() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = "testeTitulo";;
		String descricao = null;
		String url = "testeUrl";
		Message m = videoService.addVideo(1, titulo, descricao, url);
		assertEquals(m.getMensagem(), "O campo Descricao é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testAddVideosUrlNull() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = "testeTitulo";;
		String descricao = "testeDescricao";
		String url = null;
		Message m = videoService.addVideo(1, titulo, descricao, url);
		assertEquals(m.getMensagem(), "O campo Url é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testAddVideosTituloEmpty() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = "";
		String descricao = "testeDescricao";
		String url = "testeUrl";
		Message m = videoService.addVideo(1, titulo, descricao, url);
		assertEquals(m.getMensagem(), "O campo Titulo é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testAddVideosDescricaoEmpty() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = "testeTitulo";;
		String descricao = "";
		String url = "testeUrl";
		Message m = videoService.addVideo(1, titulo, descricao, url);
		assertEquals(m.getMensagem(), "O campo Descricao é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testAddVideosUrlEmpty() {
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = "testeTitulo";;
		String descricao = "testeDescricao";
		String url = "";
		Message m = videoService.addVideo(1, titulo, descricao, url);
		assertEquals(m.getMensagem(), "O campo Url é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testUpdateVideos() {
		Mockito.when(videoRepository.findById(Mockito.any())).thenReturn(optVideo);
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = "testeTitulo";
		String descricao = "testeDescricao";
		String url = "testeUrl";
		Message m = videoService.updateVideo(1, 1, titulo, descricao, url);
		assertEquals(m.getMensagem(), "Video alterado", "Teste de igualdade");
	}
	
	@Test
	void testUpdateVideosNotExists() {	 
		Mockito.when(videoRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		String titulo = "testeTitulo";
		String descricao = "testeDescricao";
		String url = "testeUrl";
		Assertions.assertThrows(NotFoundException.class, () -> {
			videoService.updateVideo(1, 1, titulo, descricao, url);
		});
	}
	
	@Test
	void testUpdateVideosTituloNull() {	 
		Mockito.when(videoRepository.findById(Mockito.any())).thenReturn(optVideo);
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = null;
		String descricao = "testeDescricao";
		String url = "testeUrl";
		Message m = videoService.updateVideo(1, 1, titulo, descricao, url);
		assertEquals(m.getMensagem(), "O campo Titulo é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testUpdateVideosDescricaoNull() {	 
		Mockito.when(videoRepository.findById(Mockito.any())).thenReturn(optVideo);
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = "testeTitulo";
		String descricao = null;
		String url = "testeUrl";
		Message m = videoService.updateVideo(1, 1, titulo, descricao, url);
		assertEquals(m.getMensagem(), "O campo Descricao é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testUpdateVideosUrlNull() {	 
		Mockito.when(videoRepository.findById(Mockito.any())).thenReturn(optVideo);
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(optCategoria);
		String titulo = "testeTitulo";
		String descricao = "testeDescricao";
		String url = null;
		Message m = videoService.updateVideo(null, 1, titulo, descricao, url);
		assertEquals(m.getMensagem(), "O campo Url é obrigatório", "Teste de igualdade");
	}
	
	@Test
	void testUpdateVideosUrlWithoutCategoria() {	 
		Mockito.when(videoRepository.findById(Mockito.any())).thenReturn(optVideo);
		Mockito.when(categoriaRepository.findById(Mockito.any())).thenReturn(Optional.empty()).thenReturn(optCategoria);
		String titulo = "testeTitulo";
		String descricao = "testeDescricao";
		String url = "testeUrl";
		Message m = videoService.updateVideo(1, 1, titulo, descricao, url);
		Video video = (Video) m.getObject();
		assertEquals(video.getCategoria().getId(), 1, "Teste de igualdade");
	}
	
	@Test
	void testDeleteVideos() {
		Mockito.when(videoRepository.findById(Mockito.any())).thenReturn(optVideo);
		assertEquals(videoService.deleteVideo(1), "Video deletado", "Teste de igualdade");
	}
	
	@Test
	void testDeleteVideosNotExists() {
		Mockito.when(videoRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		Assertions.assertThrows(NotFoundException.class, () -> {
			videoService.deleteVideo(1);
		});
	}
	
	@Test
	void testGetVideos() {
		Video video_1 = new Video();
		Video video_2 = new Video();
		List<Video> listVideos = new ArrayList<Video>();
		listVideos.add(video_1);
		listVideos.add(video_2);
		Iterable<Video> videos = listVideos;
		Mockito.when(videoRepository.findAll()).thenReturn(videos);
		assertNotNull("Teste de Objeto Not Null", videoService.getVideos());
	}

}
