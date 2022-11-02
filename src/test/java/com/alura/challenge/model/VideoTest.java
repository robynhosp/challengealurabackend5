package com.alura.challenge.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VideoTest {

	@Test
	void testGettersSetters() {
		Video video = new Video();
		video.setId(1);
		video.setTitulo("titulo");
		video.setDescricao("descricao");
		video.setUrl("url");
		
		assertEquals(video.getDescricao(), "descricao", "Teste de igualdade");
		assertEquals(video.getUrl(), "url", "Teste de igualdade");
		assertEquals(video.getId(), 1, "Teste de igualdade");
	}

}
