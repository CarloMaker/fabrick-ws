package it.fabrick.bean;

public class ErrorItem {
	
	private String code;
	private String description;
	private String params;
	
	public ErrorItem() {}
	
	public ErrorItem(String code, String description, String params) {
		super();
		this.code = code;
		this.description = description;
		this.params = params;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("code=");
		builder.append(code);
		builder.append(", description=");
		builder.append(description);
		builder.append(", params=");
		builder.append(params);
		return builder.toString();
	}
	
	
	
}
