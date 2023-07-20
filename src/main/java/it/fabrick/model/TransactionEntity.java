package it.fabrick.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@Column
	private String transactionId;
	
	@Column
	private String operationId;
	@Column
	private String accountingDate;
	
	private String valueDate;
	
	//TransactionType TypeObject;
	private float amount;
	
	private String currency;
	
	private String description;

	@ManyToOne
	@JoinColumn(name="LIST_TRANSACTION_ID",nullable = false )
	private ListaTransazioniEntity listaTransazioniEntity;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public String getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(String accountingDate) {
		this.accountingDate = accountingDate;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ListaTransazioniEntity getListaTransazioniEntity() {
		return listaTransazioniEntity;
	}

	public void setListaTransazioniEntity(ListaTransazioniEntity listaTransazioniEntity) {
		this.listaTransazioniEntity = listaTransazioniEntity;
	}
	
	
	
}
