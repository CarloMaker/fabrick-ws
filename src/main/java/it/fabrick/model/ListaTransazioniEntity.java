package it.fabrick.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import it.fabrick.model.movimenti.Transaction;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRANSAZIONI_OPERATION")
public class ListaTransazioniEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date requestDate;
	
	@OneToMany(mappedBy = "listaTransazioniEntity",fetch = FetchType.EAGER ,cascade = CascadeType.PERSIST)
	private Set<TransactionEntity> list;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Set<TransactionEntity> getList() {
		return list;
	}

	public void setList(Set<TransactionEntity> list) {
		this.list = list;
	}

	
	
	
}
