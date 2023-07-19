package it.fabrick.model.movimenti;

import java.util.List;

public class ListaTransazioniResponse {

	private List<Transaction> list;

	public List<Transaction> getList() {
		return list;
	}

	public void setList(List<Transaction> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ListaTransazioniResponse [list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	
}
