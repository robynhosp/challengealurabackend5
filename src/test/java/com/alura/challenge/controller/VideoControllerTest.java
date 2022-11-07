package com.alura.challenge.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alura.challenge.exception.NotFoundException;
import com.alura.challenge.model.Categoria;
import com.alura.challenge.model.Message;
import com.alura.challenge.model.Video;
import com.alura.challenge.model.dto.CategoriaDTO;
import com.alura.challenge.model.dto.VideoDTO;
import com.alura.challenge.service.impl.CategoriaServiceImpl;
import com.alura.challenge.service.impl.VideoServiceImpl;

@ExtendWith(MockitoExtension.class)
class VideoControllerTest {
	
	@InjectMocks
	VideoController videoController;
	
	@Mock
	VideoServiceImpl videoServiceImpl;
	
	@Mock
	CategoriaServiceImpl categoriaServiceImpl;

	@Test
	void testListVideos() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Video video_1 = new Video();
        video_1.setDescricao("descricao");
		Video video_2 = new Video();
		video_2.setDescricao("descricao");
		List<Video> listVideos = new ArrayList<Video>();
		listVideos.add(video_1);
		listVideos.add(video_2);
        when(videoServiceImpl.getVideos()).thenReturn(listVideos);
         
        List<Video> videosRet = videoController.listVideos("");
        Video video_3 = (Video) videosRet.get(0);
         
        assertEquals(video_1.getDescricao(), video_3.getDescricao(), "Teste de igualdade");
	}
	
	@Test
	void testListVideosParameterNull() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Video video_1 = new Video();
        video_1.setDescricao("descricao");
		Video video_2 = new Video();
		video_2.setDescricao("descricao");
		List<Video> listVideos = new ArrayList<Video>();
		listVideos.add(video_1);
		listVideos.add(video_2);
        when(videoServiceImpl.getVideos()).thenReturn(listVideos);
         
        List<Video> videosRet = videoController.listVideos(null);
        Video video_3 = (Video) videosRet.get(0);
         
        assertEquals(video_1.getDescricao(), video_3.getDescricao(), "Teste de igualdade");
	}
	
	@Test
	void testListVideosParameterBlank() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Video video_1 = new Video();
        video_1.setDescricao("descricao");
		Video video_2 = new Video();
		video_2.setDescricao("descricao");
		List<Video> listVideos = new ArrayList<Video>();
		listVideos.add(video_1);
		listVideos.add(video_2);
        when(videoServiceImpl.getVideos()).thenReturn(listVideos);
         
        List<Video> videosRet = videoController.listVideos(" ");
        Video video_3 = (Video) videosRet.get(0);
         
        assertEquals(video_1.getDescricao(), video_3.getDescricao(), "Teste de igualdade");
	}
	
	@Test
	void testListVideosByTitulo() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Video video_1 = new Video();
        video_1.setDescricao("descricao");
		Video video_2 = new Video();
		video_2.setDescricao("descricao");
		List<Video> listVideos = new ArrayList<Video>();
		listVideos.add(video_1);
		listVideos.add(video_2);
        when(videoServiceImpl.getVideos(Mockito.anyString())).thenReturn(listVideos);
         
        List<Video> videosRet = videoController.listVideos("nome");
        Video video_3 = (Video) videosRet.get(0);
         
        assertEquals(video_1.getDescricao(), video_3.getDescricao(), "Teste de igualdade");
	}
	
	@Test
	void testListVideosById() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Video video_1 = new Video();
        video_1.setDescricao("descricao");
        when(videoServiceImpl.getVideo(1)).thenReturn(video_1);
         
        Video video_2 = videoController.listVideoById(1);
         
        assertEquals(video_1.getDescricao(), video_2.getDescricao(), "Teste de igualdade");
	}
	
	@Test
	void testUpdateVideo() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Video video_1 = new Video();
        video_1.setDescricao("descricao");
        Message m = new Message("Video alterado", video_1);
        when(videoServiceImpl.updateVideo(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(m);
         
        Message ret = videoController.updateVideo(1, 1, "titulo", "descricao", "url");
         
        assertEquals(ret.getMensagem(), "Video alterado", "Teste de igualdade");
	}
	
	@Test
	void testAddNewVideo() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Video video_1 = new Video();
        video_1.setDescricao("descricao");
        Message m = new Message("Video adicionado", video_1);
        when(videoServiceImpl.addVideo(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(m);
         
        Message ret = videoController.addNewVideo(1, "titulo", "descricao", "url");
         
        assertEquals(ret.getMensagem(), "Video adicionado", "Teste de igualdade");
	}
	
	@Test
	void testDeleteVideo() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(videoServiceImpl.deleteVideo(Mockito.any())).thenReturn("Video deletado");
         
        String ret = videoController.deleteVideoById(1);
         
        assertEquals(ret, "Video deletado", "Teste de igualdade");
	}
		
	@Test
	void testListVideosByIException() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Video video_1 = new Video();
        video_1.setDescricao("descricao");
        NotFoundException ex = new NotFoundException();
        when(videoServiceImpl.getVideo(1)).thenThrow(ex);

		Assertions.assertThrows(NotFoundException.class, () -> {
			videoController.listVideoById(1);
		});
	}
	
	@Test
	void testListCategorias() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Categoria categoria_1 = new Categoria();
        categoria_1.setTitulo("titulo");
        Categoria categoria_2 = new Categoria();
        categoria_2.setTitulo("titulo");
		List<Categoria> listCategorias = new ArrayList<Categoria>();
		listCategorias.add(categoria_1);
		listCategorias.add(categoria_2);
		Iterable<Categoria> categorias = listCategorias;
        when(categoriaServiceImpl.getCategorias()).thenReturn(categorias);
         
        Iterable<Categoria> categoriasRet = videoController.listCategorias();
        Iterator<Categoria> it = categoriasRet.iterator();
        Categoria categoria_3 = (Categoria) it.next();
         
        assertEquals(categoria_1.getTitulo(), categoria_3.getTitulo(), "Teste de igualdade");
	}
	
	@Test
	void testListCategoriasById() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Categoria categoria_1 = new Categoria();
        categoria_1.setTitulo("titulo");
        when(categoriaServiceImpl.getCategoria(1)).thenReturn(categoria_1);
         
        Categoria categoria_2 = videoController.listCategoriaById(1);
         
        assertEquals(categoria_1.getTitulo(), categoria_2.getTitulo(), "Teste de igualdade");
	}
	
	@Test
	void testListCategoriaVideosById() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Categoria categoria = new Categoria();
        categoria.setTitulo("titulo");
        categoria.setCor("cor");
        categoria.setId(1);
        CategoriaDTO c = new CategoriaDTO();
        List<VideoDTO> videosDTO = new ArrayList<VideoDTO>();
		c.setId(categoria.getId());
		c.setTitulo(categoria.getTitulo());
		c.setCor(categoria.getCor());
		Video video_1 = new Video();
        video_1.setDescricao("descricao");
		Video video_2 = new Video();
		video_2.setDescricao("descricao");
		List<Video> listVideos = new ArrayList<Video>();
		listVideos.add(video_1);
		listVideos.add(video_2);
		Iterable<Video> videos = listVideos;
		for(Video v: videos) {
			VideoDTO dto = new VideoDTO();
			dto.setId(v.getId());
			dto.setTitulo(v.getTitulo());
			dto.setDescricao(v.getDescricao());
			dto.setUrl(v.getUrl());
			videosDTO.add(dto);
		}
		c.setVideos(videosDTO);
        when(categoriaServiceImpl.getCategoriaVideos(1)).thenReturn(c);
         
        CategoriaDTO categoriaDTO = videoController.listCategoriaVideoById(1);
         
        assertNotNull("Teste de Existencia", categoriaDTO.getVideos());
        assertNotNull("Teste de Existencia", videosDTO.get(0).getId());
        assertNull("Teste de Existencia", videosDTO.get(0).getTitulo());
        assertNotNull("Teste de Existencia", videosDTO.get(0).getDescricao());
        assertNull("Teste de Existencia", videosDTO.get(0).getUrl());
        
        assertNotNull("Teste de Existencia", c.getId());
        assertNotNull("Teste de Existencia", c.getTitulo());
        assertNotNull("Teste de Existencia", c.getCor());
	}
	
	@Test
	void testUpdateCategoria() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Categoria categoria_1 = new Categoria();
        categoria_1.setTitulo("titulo");
        Message m = new Message("Categoria alterada", categoria_1);
        when(categoriaServiceImpl.updateCategoria(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(m);
         
        Message ret = videoController.updateCategoria(1, "titulo", "cor");
         
        assertEquals(ret.getMensagem(), "Categoria alterada", "Teste de igualdade");
	}
	
	@Test
	void testAddNewCategoria() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Categoria categoria_1 = new Categoria();
        categoria_1.setTitulo("titulo");
        Message m = new Message("Categoria adicionada", categoria_1);
        when(categoriaServiceImpl.addCategoria(Mockito.any(), Mockito.any())).thenReturn(m);
         
        Message ret = videoController.addNewCategoria("titulo", "cor");
         
        assertEquals(ret.getMensagem(), "Categoria adicionada", "Teste de igualdade");
	}
	
	@Test
	void testDeleteCategoria() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(categoriaServiceImpl.deleteCategoria(Mockito.any())).thenReturn("Categoria deletada");
         
        String ret = videoController.deleteCategoriaById(1);
         
        assertEquals(ret, "Categoria deletada", "Teste de igualdade");
	}

}
