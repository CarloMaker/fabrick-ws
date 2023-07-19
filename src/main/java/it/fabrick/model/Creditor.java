package it.fabrick.model;

public class Creditor {
	private String name;
	Account AccountObject;
	Address AddressObject;

	// Getter Methods

	public String getName() {
		return name;
	}

	public Account getAccount() {
		return AccountObject;
	}

	public Address getAddress() {
		return AddressObject;
	}

	// Setter Methods

	public void setName(String name) {
		this.name = name;
	}

	public void setAccount(Account accountObject) {
		this.AccountObject = accountObject;
	}

	public void setAddress(Address addressObject) {
		this.AddressObject = addressObject;
	}
}