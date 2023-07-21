package it.fabrick.bean;

public class TransactionType {
	private String enumeration;
	private String value;

	// Getter Methods

	public String getEnumeration() {
		return enumeration;
	}

	public String getValue() {
		return value;
	}

	// Setter Methods

	public void setEnumeration(String enumeration) {
		this.enumeration = enumeration;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransactionType [enumeration=");
		builder.append(enumeration);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
	
	
}