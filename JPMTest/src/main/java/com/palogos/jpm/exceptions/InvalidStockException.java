package com.palogos.jpm.exceptions;

public class InvalidStockException extends Exception {

	private static final long serialVersionUID = -5464545653087164805L;

	public InvalidStockException() {
		super();
	}

	public InvalidStockException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidStockException(String message) {
		super(message);
	}

	public InvalidStockException(Throwable cause) {
		super(cause);
	}

}
