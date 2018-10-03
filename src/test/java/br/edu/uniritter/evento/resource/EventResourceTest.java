package br.edu.uniritter.evento.resource;

import br.edu.uniritter.evento.model.Event;
import br.edu.uniritter.evento.service.EventService;
import br.edu.uniritter.evento.service.exception.InvalidFieldException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EventResourceTest {

    private static final String EVENT_NAME = "Evento Teste";
    private static final LocalDateTime FUTURE_EVENT_DATE = LocalDateTime.of(2018, 10, 30, 9, 30);
    private static final LocalDateTime TICKETS_SALES_START_DATE = LocalDateTime.of(2018, 9, 1, 0, 0);
    private static final LocalDateTime TICKETS_SALES_END_DATE = LocalDateTime.of(2018, 10, 30, 9, 30);

    private EventResource resource;
    private Event newEvent;
    private Event createdEvent;

    private EventService service;

    @Before
    public void setup() {
        service = mock(EventService.class);
        resource = new EventResource(service);
        newEvent = new Event(null, EVENT_NAME, FUTURE_EVENT_DATE, TICKETS_SALES_START_DATE, TICKETS_SALES_END_DATE, new ArrayList<>());
        createdEvent = new Event(1L, EVENT_NAME, FUTURE_EVENT_DATE, TICKETS_SALES_START_DATE, TICKETS_SALES_END_DATE, new ArrayList<>());
    }

    @Test
    public void salvarOk() throws Exception{
        when(service.save(any(Event.class))).thenReturn(createdEvent);
        ResponseEntity responseEntity = resource.add(newEvent);
        assertEquals(201, responseEntity.getStatusCode().value());
    }

    @Test(expected = InvalidFieldException.class)
    public void salvarErro() throws Exception{
        when(service.save(any(Event.class))).thenThrow(new InvalidFieldException("Erro ao salvar"));
        ResponseEntity responseEntity = resource.add(newEvent);
    }

}
