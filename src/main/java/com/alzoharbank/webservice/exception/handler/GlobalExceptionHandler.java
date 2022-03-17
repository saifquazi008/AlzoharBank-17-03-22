package com.alzoharbank.webservice.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alzoharbank.webservice.exception.AccountNotFound;
import com.alzoharbank.webservice.exception.BankTransactionFailed;
import com.alzoharbank.webservice.exception.InvalidTransaction;

@ControllerAdvice
public class GlobalExceptionHandler {

	ExceptionResponse response;

	@ExceptionHandler(value = AccountNotFound.class)
	public ResponseEntity<ExceptionResponse> accountNotFound(AccountNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(value = BankTransactionFailed.class)
	public ResponseEntity<ExceptionResponse> bankTransactionFailed(BankTransactionFailed exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.BAD_REQUEST.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = InvalidTransaction.class)
	public ResponseEntity<ExceptionResponse> invalidTransaction(InvalidTransaction exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.BAD_REQUEST.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);

	}

}
