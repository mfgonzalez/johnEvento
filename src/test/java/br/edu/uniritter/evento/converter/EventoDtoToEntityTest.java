package br.edu.uniritter.evento.converter;

import br.edu.uniritter.evento.dto.EventDto;
import br.edu.uniritter.evento.model.Event;
import br.edu.uniritter.evento.model.TicketType;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EventoDtoToEntityTest {

    private static final String EVENT_NAME = "Evento Teste";
    private static final LocalDateTime FUTURE_EVENT_DATE = LocalDateTime.of(2018, 10, 30, 9, 30);
    private static final LocalDateTime TICKETS_SALES_START_DATE = LocalDateTime.of(2018, 9, 1, 0, 0);
    private static final LocalDateTime TICKETS_SALES_END_DATE = LocalDateTime.of(2018, 10, 30, 9, 30);

    private EventoDtoToEntity converter;

    @Before
    public void setup() {
        converter = new EventoDtoToEntity();
    }

    @Test
    public void converteOk() {
        EventDto dto = new EventDto(EVENT_NAME, FUTURE_EVENT_DATE, TICKETS_SALES_START_DATE, TICKETS_SALES_END_DATE, new ArrayList<TicketType>());
        Event event = converter.convert(dto);
        assertEquals(dto.getName(), event.getName());
        assertEquals(dto.getDate(), event.getDate());
        assertEquals(dto.getTicketsSalesStartDateTime(), event.getTicketsSalesStartDateTime());
        assertEquals(dto.getTicketsSalesEndDateTime(), event.getTicketsSalesEndDateTime());
        assertEquals(dto.getAvailableTicketTypes(), event.getAvailableTicketTypes());
    }
}
