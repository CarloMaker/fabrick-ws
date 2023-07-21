package it.fabrick.bean;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {

	private String status;
	private List<ErrorItem> errors = new ArrayList<>();
	
	public ErrorResponse() {
		
	}
	
	public ErrorResponse(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ErrorItem> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorItem> errors) {
		this.errors = errors;
	}
	public void addError(String status , String description) {
		this.errors.add(new ErrorItem(status, description,""));
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("status=");
		builder.append(status);
		builder.append(", errors=");
		builder.append(errors);
		return builder.toString();
	}

}
