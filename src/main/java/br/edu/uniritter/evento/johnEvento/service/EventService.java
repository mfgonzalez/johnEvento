package br.edu.uniritter.evento.johnEvento.service;

import br.edu.uniritter.evento.johnEvento.model.Event;
import br.edu.uniritter.evento.johnEvento.service.exception.InvalidFieldException;

public interface EventService {
    Event save(Event event) throws InvalidFieldException;
}