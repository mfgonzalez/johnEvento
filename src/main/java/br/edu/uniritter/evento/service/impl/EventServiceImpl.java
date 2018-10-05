package br.edu.uniritter.evento.service.impl;

import br.edu.uniritter.evento.converter.EventEntityToDto;
import br.edu.uniritter.evento.converter.EventoDtoToEntity;
import br.edu.uniritter.evento.dto.EventDto;
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
    private EventoDtoToEntity dtoToEntity;
    private EventEntityToDto entityToDto;


    @Autowired
    public EventServiceImpl(EventRepository repository,
                            EventoDtoToEntity dtoToEntity,
                            EventEntityToDto entityToDto) {
        this.repository = repository;
        this.dtoToEntity = dtoToEntity;
        this.entityToDto = entityToDto;
    }

    public EventDto save(EventDto dto) throws InvalidFieldException {
        Event event = dtoToEntity.convert(dto);
        NameValidator.validate(event.getName());
        DateValidator.validate(event.getDate());
        SalesDateValidator.validate(event.getTicketsSalesStartDateTime(), event.getTicketsSalesEndDateTime());
        TicketsValidator.validate(event.getAvailableTicketTypes());
        event = repository.save(event);
        return entityToDto.convert(event);
    }

}