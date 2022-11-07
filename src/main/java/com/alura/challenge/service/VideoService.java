package com.alura.challenge.service;

import java.util.List;

import com.alura.challenge.model.Message;
import com.alura.challenge.model.Video;

public interface VideoService {

	Video getVideo(Integer id);
	
	List<Video> getVideos();
	
	List<Video> getVideos(String titulo);
	
	Message addVideo(Integer categoriaId, String titulo, String descricao, String url);
	
	Message updateVideo(Integer id, Integer categoriaId, String titulo, String descricao, String url) ;
	
	String deleteVideo(Integer id);
	
	Boolean validFields(String string);
}
