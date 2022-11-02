package com.alura.challenge.exception;

public class AlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 5097975036976749370L;
	
	private String message;
	 
    public AlreadyExistsException() {}
 
    public AlreadyExistsException(String msg){
        super(msg);
        this.message = msg;
    }
}
