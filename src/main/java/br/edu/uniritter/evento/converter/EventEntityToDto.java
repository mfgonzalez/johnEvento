package br.edu.uniritter.evento.converter;

import br.edu.uniritter.evento.dto.EventDto;
import br.edu.uniritter.evento.model.Event;
import org.springframework.core.convert.converter.Converter;

public class EventEntityToDto implements Converter<Event, EventDto> {
    @Override
    public EventDto convert(Event source) {
        return new EventDto(source.getName(),
                source.getDate(),
                source.getTicketsSalesStartDateTime(),
                source.getTicketsSalesEndDateTime(),
                source.getAvailableTicketTypes());
    }
}
