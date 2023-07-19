package it.fabrick.model;

public class LegalPersonBeneficiary {
	 private String fiscalCode = null;
	 private String legalRepresentativeFiscalCode = null;


	 // Getter Methods 

	 public String getFiscalCode() {
	  return fiscalCode;
	 }

	 public String getLegalRepresentativeFiscalCode() {
	  return legalRepresentativeFiscalCode;
	 }

	 // Setter Methods 

	 public void setFiscalCode(String fiscalCode) {
	  this.fiscalCode = fiscalCode;
	 }

	 public void setLegalRepresentativeFiscalCode(String legalRepresentativeFiscalCode) {
	  this.legalRepresentativeFiscalCode = legalRepresentativeFiscalCode;
	 }
}