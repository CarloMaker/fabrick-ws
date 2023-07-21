package it.fabrick.bean;

public class Transaction {
	private String transactionId;
	private String operationId;
	private String accountingDate;
	private String valueDate;
	TransactionType TypeObject;
	private float amount;
	private String currency;
	private String description;

	// Getter Methods

	public String getTransactionId() {
		return transactionId;
	}

	public String getOperationId() {
		return operationId;
	}

	public String getAccountingDate() {
		return accountingDate;
	}

	public String getValueDate() {
		return valueDate;
	}

	public TransactionType getType() {
		return TypeObject;
	}

	public float getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public String getDescription() {
		return description;
	}

	// Setter Methods

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public void setAccountingDate(String accountingDate) {
		this.accountingDate = accountingDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public void setType(TransactionType typeObject) {
		this.TypeObject = typeObject;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [transactionId=");
		builder.append(transactionId);
		builder.append(", operationId=");
		builder.append(operationId);
		builder.append(", accountingDate=");
		builder.append(accountingDate);
		builder.append(", valueDate=");
		builder.append(valueDate);
		builder.append(", TypeObject=");
		builder.append(TypeObject);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	
}
