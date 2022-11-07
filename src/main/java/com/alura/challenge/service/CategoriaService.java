package com.alura.challenge.service;

import com.alura.challenge.model.Categoria;
import com.alura.challenge.model.Message;
import com.alura.challenge.model.dto.CategoriaDTO;

public interface CategoriaService {
	
	Categoria getCategoria(Integer id);
	
	CategoriaDTO getCategoriaVideos(Integer id);
	
	Iterable<Categoria> getCategorias();

	Message addCategoria(String titulo, String cor);
	
	Message updateCategoria(Integer id, String titulo, String cor);
	
	String deleteCategoria(Integer id);
	
	Boolean validFields(String string);
}
