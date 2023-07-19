
package it.fabrick.model;

public class MoneyTransferRequestBody {

	Creditor CreditorObject;
	private String executionDate;
	private String uri;
	private String description;
	private float amount;
	private String currency;
	private boolean isUrgent;
	private boolean isInstant;
	private String feeType;
	private String feeAccountId;
	TaxRelief TaxReliefObject;

	// Getter Methods

	public Creditor getCreditor() {
		return CreditorObject;
	}

	public String getExecutionDate() {
		return executionDate;
	}

	public String getUri() {
		return uri;
	}

	public String getDescription() {
		return description;
	}

	public float getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public boolean getIsUrgent() {
		return isUrgent;
	}

	public boolean getIsInstant() {
		return isInstant;
	}

	public String getFeeType() {
		return feeType;
	}

	public String getFeeAccountId() {
		return feeAccountId;
	}

	public TaxRelief getTaxRelief() {
		return TaxReliefObject;
	}

	// Setter Methods

	public void setCreditor(Creditor creditorObject) {
		this.CreditorObject = creditorObject;
	}

	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setIsUrgent(boolean isUrgent) {
		this.isUrgent = isUrgent;
	}

	public void setIsInstant(boolean isInstant) {
		this.isInstant = isInstant;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public void setFeeAccountId(String feeAccountId) {
		this.feeAccountId = feeAccountId;
	}

	public void setTaxRelief(TaxRelief taxReliefObject) {
		this.TaxReliefObject = taxReliefObject;
	}
}