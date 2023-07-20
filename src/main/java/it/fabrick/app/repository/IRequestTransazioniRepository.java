package it.fabrick.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.fabrick.model.ListaTransazioniEntity;

@Repository 
public interface IRequestTransazioniRepository extends CrudRepository<ListaTransazioniEntity, Integer>{
	

}
