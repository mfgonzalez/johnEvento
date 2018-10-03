package br.edu.uniritter.evento.repository;

import br.edu.uniritter.evento.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>{
}