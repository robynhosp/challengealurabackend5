package com.alura.challenge.exception;

public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = 6427914963744708969L;

	private String message;

	public NotFoundException() {}

	public NotFoundException(String msg){
		super(msg);
		this.message = msg;
	}
}
