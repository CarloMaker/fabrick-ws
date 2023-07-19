package it.fabrick.bean;

import java.util.List;

public class RestOutput<T> {

	private static final String OK = "OK";

	private String status;
	private List<String> error;
	private T payload;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getError() {
		return error;
	}

	public void setError(List<String> error) {
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

	public String getFormattedErrors() {
		return error.toString();
	}

}
