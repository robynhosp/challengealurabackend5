package com.alura.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.alura.challenge.model.Video;

public interface VideoRepository extends CrudRepository<Video, Integer> {

	Iterable<Video> findByCategoriaId(Integer id);

}
