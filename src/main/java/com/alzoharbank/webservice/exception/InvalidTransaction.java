package com.alzoharbank.webservice.exception;

public class InvalidTransaction extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTransaction(String message) {
		super(message);
	}
}
