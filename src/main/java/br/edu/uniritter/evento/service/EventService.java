package br.edu.uniritter.evento.service;

import br.edu.uniritter.evento.model.Event;
import br.edu.uniritter.evento.service.exception.InvalidFieldException;

public interface EventService {
    Event save(Event event) throws InvalidFieldException;
}