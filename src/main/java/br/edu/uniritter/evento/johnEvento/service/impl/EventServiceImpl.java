package br.edu.uniritter.evento.johnEvento.service.impl;

import br.edu.uniritter.evento.johnEvento.model.Event;
import br.edu.uniritter.evento.johnEvento.repository.EventRepository;
import br.edu.uniritter.evento.johnEvento.service.EventService;
import br.edu.uniritter.evento.johnEvento.service.exception.InvalidFieldException;
import br.edu.uniritter.evento.johnEvento.validator.DateValidator;
import br.edu.uniritter.evento.johnEvento.validator.NameValidator;
import br.edu.uniritter.evento.johnEvento.validator.SalesDateValidator;
import br.edu.uniritter.evento.johnEvento.validator.TicketsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository repository;

    @Autowired
    public EventServiceImpl(EventRepository repository, NameValidator nameValidator, DateValidator dateValidator) {
        this.repository = repository;
    }

    public Event save(Event event) throws InvalidFieldException {
        NameValidator.validate(event.getName());
        DateValidator.validate(event.getDate());
        SalesDateValidator.validate(event.getTicketsSalesStartDateTime(), event.getTicketsSalesEndDateTime());
        TicketsValidator.validate(event.getAvailableTicketTypes());
        return repository.save(event);
    }

}