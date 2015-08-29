package com.palogos.jpm.exceptions;

public class InvalidStockIndexException extends Exception {

	public InvalidStockIndexException(String message) {
		super(message);
	}

	public InvalidStockIndexException() {
		super("Invalid stock index exception");
	}

}
