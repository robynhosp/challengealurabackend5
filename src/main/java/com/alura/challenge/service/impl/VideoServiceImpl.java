package com.alura.challenge.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.challenge.exception.NotFoundException;
import com.alura.challenge.model.Categoria;
import com.alura.challenge.model.Message;
import com.alura.challenge.model.Video;
import com.alura.challenge.repository.CategoriaRepository;
import com.alura.challenge.repository.VideoRepository;
import com.alura.challenge.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

	private static final int CATEGORIA_PADRAO = 1;

	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Video getVideo(Integer id) {
		Optional<Video> video = videoRepository.findById(id);
		if(!video.isPresent()) {
			throw new NotFoundException("Não encontrado");
		}
		return video.get();
	}

	public List<Video> getVideos() {
		return StreamSupport.stream(videoRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}
	
	public List<Video> getVideos(String titulo) {
		System.out.println(titulo);
		List<Video> video = videoRepository.findVideoPorTitulo(titulo);
		if(video.isEmpty()) {
			throw new NotFoundException("Não encontrado");
		}
		return video;
	}

	public Message addVideo(Integer categoriaId, String titulo, String descricao, String url)
	{
		Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
		if(!categoria.isPresent()) {
			categoria = categoriaRepository.findById(CATEGORIA_PADRAO);
		}
		if(!validFields(titulo)) {
			return new Message("O campo Titulo é obrigatório");
		}
		if(!validFields(descricao)) {
			return new Message("O campo Descricao é obrigatório");
		}
		if(!validFields(url)) {
			return new Message("O campo Url é obrigatório");
		}
		
		Video v = new Video();
		v.setCategoria(categoria.get());
		v.setTitulo(titulo);
		v.setDescricao(descricao);
		v.setUrl(url);
		videoRepository.save(v);
		return new Message("Video adicionado", v);
		
	}
	
	public Message updateVideo(Integer id, Integer categoriaId, String titulo, String descricao, String url) {
		Optional<Video> optVideo = videoRepository.findById(id);
		Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
		
		if(!optVideo.isPresent()) {
			throw new NotFoundException("Video não encontrado");
		}
		
		if(!categoria.isPresent()) {
			categoria = categoriaRepository.findById(CATEGORIA_PADRAO);
		}
		
		if(!validFields(titulo)) {
			return new Message("O campo Titulo é obrigatório");
		}
		if(!validFields(descricao)) {
			return new Message("O campo Descricao é obrigatório");
		}
		if(!validFields(url)) {
			return new Message("O campo Url é obrigatório");
		}
		
		Video video = optVideo.get();
		video.setCategoria(categoria.get());
		video.setTitulo(titulo);
		video.setDescricao(descricao);
		video.setUrl(url);
		videoRepository.save(video);
		return new Message("Video alterado", video);
	}
	
	public String deleteVideo(Integer id) {
		
		Optional<Video> optVideo = videoRepository.findById(id);
		if(!optVideo.isPresent()) {
			throw new NotFoundException("Video não encontrado");
		} 
		
		videoRepository.delete(optVideo.get());
		return "Video deletado";
	}
	
	public Boolean validFields(String string) {
		if(string!=null && !string.isEmpty()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
