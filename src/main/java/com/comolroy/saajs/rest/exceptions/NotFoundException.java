package com.comolroy.saajs.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7593697088818129029L;

	public NotFoundException() {

	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

}
