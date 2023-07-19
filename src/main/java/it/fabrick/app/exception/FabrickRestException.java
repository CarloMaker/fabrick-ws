package it.fabrick.app.exception;

import it.fabrick.model.ErrorResponse;

public class FabrickRestException extends Exception {
	
	private String statusCode;
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
	
	public FabrickRestException(ErrorResponse errorResponse) {
		super();
		this.errorResponse = errorResponse;
	}
	
}
