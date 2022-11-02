package com.alura.challenge.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class MessageTest {

	@Test
	void testMessage() {
		Video video = new Video();
		Message message = new Message("teste", video);
		message.setMensagem("Mudando");
		message.setObject(video);
		assertEquals("Commparacao de valores", message.getMensagem(), "Mudando");
	}

}
