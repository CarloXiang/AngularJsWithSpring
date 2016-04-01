package com.comolroy.saajs.core.services.exceptions;

public class BlogNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2145915985776440917L;
	
	public BlogNotFoundException(){
		
	}
	
	public BlogNotFoundException(String message){
		super(message);
	}
	
	public BlogNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
}
