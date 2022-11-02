package com.alura.challenge.service;

import com.alura.challenge.model.Message;
import com.alura.challenge.model.Video;

public interface VideoService {

	Video getVideo(Integer id);
	
	Message addVideo(Integer categoriaId, String titulo, String descricao, String url);
}
