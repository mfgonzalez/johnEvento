package br.edu.uniritter.evento.service.impl;

import br.edu.uniritter.evento.model.Event;
import br.edu.uniritter.evento.repository.EventRepository;
import br.edu.uniritter.evento.service.EventService;
import br.edu.uniritter.evento.service.exception.InvalidFieldException;
import br.edu.uniritter.evento.validator.DateValidator;
import br.edu.uniritter.evento.validator.NameValidator;
import br.edu.uniritter.evento.validator.SalesDateValidator;
import br.edu.uniritter.evento.validator.TicketsValidator;
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