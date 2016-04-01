package com.comolroy.saajs.core.services.exceptions;

public class AccountDoesNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountDoesNotExistException(){
	}
	
	public AccountDoesNotExistException(String message){
		super(message);
	}
	
	public AccountDoesNotExistException(Throwable cause){
		super(cause);
	}
	
	public AccountDoesNotExistException(String message, Throwable cause){
		super(message,cause);
	}
}
