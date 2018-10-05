package br.edu.uniritter.evento.converter;

import br.edu.uniritter.evento.dto.EventDto;
import br.edu.uniritter.evento.model.Event;
import org.springframework.core.convert.converter.Converter;

public class EventoDtoToEntity implements Converter<EventDto, Event> {
    @Override
    public Event convert(EventDto source) {
        return new Event(null,
                source.getName(),
                source.getDate(),
                source.getTicketsSalesStartDateTime(),
                source.getTicketsSalesEndDateTime(),
                source.getAvailableTicketTypes());
    }
}
