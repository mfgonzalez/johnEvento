package br.edu.uniritter.evento.service;

import br.edu.uniritter.evento.dto.EventDto;
import br.edu.uniritter.evento.service.exception.InvalidFieldException;

public interface EventService {
    EventDto save(EventDto event) throws InvalidFieldException;
}