package br.edu.uniritter.evento.johnEvento.repository;

import br.edu.uniritter.evento.johnEvento.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>{
}