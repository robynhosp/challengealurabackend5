package com.alura.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alura.challenge.model.Video;

public interface VideoRepository extends CrudRepository<Video, Integer> {

	List<Video> findByCategoriaId(Integer id);
	
	@Query("SELECT v FROM Video v WHERE v.titulo like %:titulo%")
	List<Video> findVideoPorTitulo(String titulo);

}
