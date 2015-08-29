package com.palogos.jpm.exceptions;

public class InvalidIndexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1791048004005168358L;

	public InvalidIndexException() {
		super();
	}

	public InvalidIndexException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidIndexException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidIndexException(String message) {
		super(message);
	}

	public InvalidIndexException(Throwable cause) {
		super(cause);
	}

}
