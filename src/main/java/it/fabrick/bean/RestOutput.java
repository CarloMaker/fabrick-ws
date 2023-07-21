package it.fabrick.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RestOutput<T> {

	public static final String OK = "OK";
	public static final String KO = "KO";

	private String status;
	
	private List<ErrorItem> error = new ArrayList<>();
	
	private T payload;
	
	
	public RestOutput() {
	}
	
	public RestOutput(String status) {
		super();
		this.status = status;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public List<ErrorItem> getError() {
		return error;
	}

	public void setError(List<ErrorItem> error) {
		this.error = error;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public boolean isValid() {
		return OK.equals(status);
	}

	public void addError(String code, String description, String params) {
		error.add(new ErrorItem(code, description, params));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RestOutput [status=");
		builder.append(status);
		builder.append(", error=");
		builder.append(error);
		builder.append(", payload=");
		builder.append(payload);
		builder.append("]");
		return builder.toString();
	}

	

	
}
