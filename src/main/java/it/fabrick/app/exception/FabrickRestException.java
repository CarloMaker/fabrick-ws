package it.fabrick.app.exception;

import org.springframework.http.HttpStatus;

import it.fabrick.bean.ExceptionType;
import it.fabrick.model.ErrorResponse;

public class FabrickRestException extends Exception {
	
	private ExceptionType exceptionType;
	private ErrorResponse errorResponse; 

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	public FabrickRestException(String msg) {
		super(msg);
	}
	
	public FabrickRestException(ExceptionType exceptionType, String msg) {
		super(msg);
		this.exceptionType = exceptionType;
	}
	
	public FabrickRestException(ExceptionType exceptionType,  ErrorResponse errorResponse) {
		super();
		this.exceptionType = exceptionType;
		this.errorResponse = errorResponse;
	}
	
}
