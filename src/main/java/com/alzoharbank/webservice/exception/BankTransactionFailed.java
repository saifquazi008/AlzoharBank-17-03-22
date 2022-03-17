package com.alzoharbank.webservice.exception;

public class BankTransactionFailed extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BankTransactionFailed(String message) {
		super(message);

	}

}
