package com.alura.challenge.model;

public class Message {

	private String mensagem;
	private Object object;
	
	public Message(String message, Object object) {
		this.mensagem = message;
		this.object = object;
	}
	
	public Message(String message) {
		this.mensagem = message;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
	
	
}
