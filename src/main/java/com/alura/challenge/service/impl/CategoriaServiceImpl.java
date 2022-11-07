package com.alura.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.challenge.exception.NotFoundException;
import com.alura.challenge.model.Categoria;
import com.alura.challenge.model.Message;
import com.alura.challenge.model.Video;
import com.alura.challenge.model.dto.CategoriaDTO;
import com.alura.challenge.model.dto.VideoDTO;
import com.alura.challenge.repository.CategoriaRepository;
import com.alura.challenge.repository.VideoRepository;
import com.alura.challenge.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private VideoRepository videoRepository;

	public Categoria getCategoria(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(!categoria.isPresent()) {
			throw new NotFoundException("Categoria não encontrada");
		}
		return categoria.get();
		
	}
	
	public CategoriaDTO getCategoriaVideos(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if(!categoria.isPresent()) {
			throw new NotFoundException("Categoria não encontrada");
		}
		List<Video> videos = videoRepository.findByCategoriaId(id);
		List<VideoDTO> videosDTO = new ArrayList<VideoDTO>();
		CategoriaDTO c = new CategoriaDTO();
		c.setId(categoria.get().getId());
		c.setTitulo(categoria.get().getTitulo());
		c.setCor(categoria.get().getCor());
		for(Video v: videos) {
			VideoDTO dto = new VideoDTO();
			dto.setId(v.getId());
			dto.setTitulo(v.getTitulo());
			dto.setDescricao(v.getDescricao());
			dto.setUrl(v.getUrl());
			videosDTO.add(dto);
		}
		c.setVideos(videosDTO);
		return c;
		
	}

	public Iterable<Categoria> getCategorias() {
		return categoriaRepository.findAll();
	}

	public Message addCategoria(String titulo, String cor)
	{
		if(!validFields(titulo)) {
			return new Message("O campo Titulo é obrigatório");
		}
		if(!validFields(cor)) {
			return new Message("O campo Cor é obrigatório");
		}
		Categoria c = new Categoria();
		c.setTitulo(titulo);
		c.setCor(cor);
		categoriaRepository.save(c);
		return new Message("Categoria adicionada", c);
	}
	
	public Message updateCategoria(Integer id, String titulo, String cor) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
				
		if(!categoria.isPresent()) {
			throw new NotFoundException("Categoria não encontrada");
		}
		
		if(!validFields(titulo)) {
			return new Message("O campo Titulo é obrigatório");
		}
		if(!validFields(cor)) {
			return new Message("O campo Cor é obrigatório");
		}
		
		Categoria c = categoria.get();
		c.setTitulo(titulo);
		c.setCor(cor);
		categoriaRepository.save(c);
		return new Message("Categoria alterada", c);
	}
	
	public String deleteCategoria(Integer id) {
		
		Optional<Categoria> optCategoria = categoriaRepository.findById(id);
		if(!optCategoria.isPresent()) {
			throw new NotFoundException("Categoria não encontrada");
		} 
		
		categoriaRepository.delete(optCategoria.get());
		return "Categoria deletada";
	}

	public Boolean validFields(String string) {
		if(string!=null && !string.isEmpty()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
