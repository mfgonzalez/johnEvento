package br.edu.uniritter.evento.converter;

import br.edu.uniritter.evento.dto.EventDto;
import br.edu.uniritter.evento.model.Event;
import br.edu.uniritter.evento.model.TicketType;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class EventEntityToDtoTest {

    private static final String EVENT_NAME = "Evento Teste";
    private static final LocalDateTime FUTURE_EVENT_DATE = LocalDateTime.of(2018, 10, 30, 9, 30);
    private static final LocalDateTime TICKETS_SALES_START_DATE = LocalDateTime.of(2018, 9, 1, 0, 0);
    private static final LocalDateTime TICKETS_SALES_END_DATE = LocalDateTime.of(2018, 10, 30, 9, 30);

    private EventEntityToDto converter;

    @Before
    public void setup() {
        converter = new EventEntityToDto();
    }

    @Test
    public void converteOk() {
        Event entity = new Event(1L, EVENT_NAME, FUTURE_EVENT_DATE, TICKETS_SALES_START_DATE, TICKETS_SALES_END_DATE, new ArrayList<TicketType>());
        EventDto dto = converter.convert(entity);
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getDate(), dto.getDate());
        assertEquals(entity.getTicketsSalesStartDateTime(), dto.getTicketsSalesStartDateTime());
        assertEquals(entity.getTicketsSalesEndDateTime(), dto.getTicketsSalesEndDateTime());
        assertEquals(entity.getAvailableTicketTypes(), dto.getAvailableTicketTypes());
    }

}
