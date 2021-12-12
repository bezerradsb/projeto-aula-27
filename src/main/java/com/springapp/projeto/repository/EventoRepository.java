package com.springapp.projeto.repository;

import org.springframework.data.repository.CrudRepository;

import com.springapp.projeto.model.Evento;

public interface EventoRepository extends CrudRepository<Evento, String> {
	Evento findByCodigoEvento(long codigoEvento);
}
