package it.fabrick.bean;

public class Address {
	 private String address = null;
	 private String city = null;
	 private String countryCode = null;


	 // Getter Methods 

	 public String getAddress() {
	  return address;
	 }

	 public String getCity() {
	  return city;
	 }

	 public String getCountryCode() {
	  return countryCode;
	 }

	 // Setter Methods 

	 public void setAddress(String address) {
	  this.address = address;
	 }

	 public void setCity(String city) {
	  this.city = city;
	 }

	 public void setCountryCode(String countryCode) {
	  this.countryCode = countryCode;
	 }
}