package com.comolroy.saajs.core.services.exceptions;

public class AccountExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8551367546432120011L;

	public AccountExistsException() {

	}

	public AccountExistsException(String message) {
		super(message);
	}

	public AccountExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
