package com.comolroy.saajs.core.services.exceptions;

public class BlogExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2043132078075812314L;

	public BlogExistsException() {

	}

	public BlogExistsException(String message) {
		super(message);
	}

	public BlogExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
