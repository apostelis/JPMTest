package com.palogos.jpm.exceptions;

public class InvalidTradeException extends Exception {

	private static final long serialVersionUID = 8071293185837065131L;

	public InvalidTradeException() {
		super();
	}

	public InvalidTradeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidTradeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidTradeException(String message) {
		super(message);
	}

	public InvalidTradeException(Throwable cause) {
		super(cause);
	}

}
