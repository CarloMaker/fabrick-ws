package it.fabrick.model;

public class TaxRelief {
	private String taxReliefId;
	private boolean isCondoUpgrade;
	private String creditorFiscalCode;
	private String beneficiaryType;
	NaturalPersonBeneficiary NaturalPersonBeneficiaryObject;
	LegalPersonBeneficiary LegalPersonBeneficiaryObject;

	// Getter Methods

	public String getTaxReliefId() {
		return taxReliefId;
	}

	public boolean getIsCondoUpgrade() {
		return isCondoUpgrade;
	}

	public String getCreditorFiscalCode() {
		return creditorFiscalCode;
	}

	public String getBeneficiaryType() {
		return beneficiaryType;
	}

	public NaturalPersonBeneficiary getNaturalPersonBeneficiary() {
		return NaturalPersonBeneficiaryObject;
	}

	public LegalPersonBeneficiary getLegalPersonBeneficiary() {
		return LegalPersonBeneficiaryObject;
	}

	// Setter Methods

	public void setTaxReliefId(String taxReliefId) {
		this.taxReliefId = taxReliefId;
	}

	public void setIsCondoUpgrade(boolean isCondoUpgrade) {
		this.isCondoUpgrade = isCondoUpgrade;
	}

	public void setCreditorFiscalCode(String creditorFiscalCode) {
		this.creditorFiscalCode = creditorFiscalCode;
	}

	public void setBeneficiaryType(String beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}

	public void setNaturalPersonBeneficiary(NaturalPersonBeneficiary naturalPersonBeneficiaryObject) {
		this.NaturalPersonBeneficiaryObject = naturalPersonBeneficiaryObject;
	}

	public void setLegalPersonBeneficiary(LegalPersonBeneficiary legalPersonBeneficiaryObject) {
		this.LegalPersonBeneficiaryObject = legalPersonBeneficiaryObject;
	}
}
