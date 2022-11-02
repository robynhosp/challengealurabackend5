package com.alura.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alura.challenge.exception.AlreadyExistsException;
import com.alura.challenge.exception.ErrorResponse;
import com.alura.challenge.exception.NotFoundException;
import com.alura.challenge.model.Categoria;
import com.alura.challenge.model.Message;
import com.alura.challenge.model.Video;
import com.alura.challenge.model.dto.CategoriaDTO;
import com.alura.challenge.service.impl.CategoriaServiceImpl;
import com.alura.challenge.service.impl.VideoServiceImpl;

@Controller
@RestController
public class VideoController {

	private static final String CATEGORIA_PADRAO = "1";

	@Autowired
	private VideoServiceImpl videoServiceImpl;
	
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;

	@GetMapping("/videos")
	public @ResponseBody Iterable<Video> listVideos(){

		return videoServiceImpl.getVideos();
	}
	
	@GetMapping("/categorias")
	public @ResponseBody Iterable<Categoria> listCategorias(){

		return categoriaServiceImpl.getCategorias();
	}

	@GetMapping("/videos/{id}")
	public Video listVideoById(@PathVariable("id") Integer id)
	{
		return videoServiceImpl.getVideo(id);
	}
	
	@GetMapping("/categorias/{id}")
	public Categoria listCategoriaById(@PathVariable("id") Integer id)
	{
		return categoriaServiceImpl.getCategoria(id);
	}
	
	@GetMapping("/categorias/{id}/videos")
	public CategoriaDTO listCategoriaVideoById(@PathVariable("id") Integer id)
	{
		return categoriaServiceImpl.getCategoriaVideos(id);
	}

	@PutMapping(path="/videos")
	public @ResponseBody Message updateVideo (@RequestParam Integer id
			                                , @RequestParam(required = false, defaultValue = CATEGORIA_PADRAO) Integer categoriaId
			                                , @RequestParam String titulo
			                                , @RequestParam String descricao
			                                , @RequestParam String url) {
		return videoServiceImpl.updateVideo(id, categoriaId, titulo, descricao, url);
	}
	
	@PutMapping(path="/categorias")
	public @ResponseBody Message updateCategoria (@RequestParam Integer id
			                                    , @RequestParam String titulo
			                                    , @RequestParam String cor) {
		return categoriaServiceImpl.updateCategoria(id, titulo, cor);
	}
	
	@PostMapping(path="/videos")
	public @ResponseBody Message addNewVideo (@RequestParam(required = false, defaultValue = CATEGORIA_PADRAO) Integer categoriaId
			                                , @RequestParam String titulo
			                                , @RequestParam String descricao
			                                , @RequestParam String url) {
		return videoServiceImpl.addVideo(categoriaId, titulo, descricao, url);
	}
	
	@PostMapping(path="/categorias")
	public @ResponseBody Message addNewCategoria (@RequestParam String titulo
			                                    , @RequestParam String cor) {
		return categoriaServiceImpl.addCategoria(titulo, cor);
	}
	
	
	@DeleteMapping("/videos/{id}")
	public String deleteVideoById(@PathVariable("id") Integer id)
	{
		return videoServiceImpl.deleteVideo(id);
	}
	
	@DeleteMapping("/categorias/{id}")
	public String deleteCategoriaById(@PathVariable("id") Integer id)
	{
		return categoriaServiceImpl.deleteCategoria(id);
	}

	@ExceptionHandler(value= AlreadyExistsException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleVideoAlreadyExistsException(AlreadyExistsException ex){
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}
	
	@ExceptionHandler(value= NotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleVideoNotFoundException(NotFoundException ex){
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

}
