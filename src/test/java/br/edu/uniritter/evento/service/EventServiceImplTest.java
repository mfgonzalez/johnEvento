package br.edu.uniritter.evento.service;

import br.edu.uniritter.evento.converter.EventEntityToDto;
import br.edu.uniritter.evento.converter.EventoDtoToEntity;
import br.edu.uniritter.evento.dto.EventDto;
import br.edu.uniritter.evento.model.AudienceTicket;
import br.edu.uniritter.evento.model.Event;
import br.edu.uniritter.evento.model.TicketType;
import br.edu.uniritter.evento.repository.EventRepository;
import br.edu.uniritter.evento.service.exception.InvalidFieldException;
import br.edu.uniritter.evento.service.impl.EventServiceImpl;
import br.edu.uniritter.evento.validator.DateValidator;
import br.edu.uniritter.evento.validator.NameValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EventServiceImplTest {

    private static final String EVENT_NAME = "Evento Teste";
    private static final String EVENT_NAME_151_CHARS = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901";
    private static final LocalDateTime FUTURE_EVENT_DATE = LocalDateTime.of(2018, 10, 30, 9, 30);
    private static final LocalDateTime PAST_EVENT_DATE = LocalDateTime.of(2017, 10, 30, 9, 30);
    private static final LocalDateTime TICKETS_SALES_START_DATE = LocalDateTime.of(2018, 9, 1, 0, 0);
    private static final LocalDateTime TICKETS_SALES_END_DATE = LocalDateTime.of(2018, 10, 30, 9, 30);
    private static final LocalDateTime INVALID_TICKETS_SALES_START_DATE = LocalDateTime.of(2019, 9, 1, 0, 0);

    private EventService service;

    private EventDto newEventDto;
    private Event createdEvent;
    private List<TicketType> duplicatedTicketTypes;


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @MockBean
    private EventRepository repository;

    @MockBean
    private NameValidator nameValidator;

    @MockBean
    private DateValidator dateValidator;

    @MockBean
    private EventoDtoToEntity dtoToEntity;

    @MockBean
    private EventEntityToDto entityToDto;

    @Before
    public void setup() {
        service = new EventServiceImpl(repository, dtoToEntity, entityToDto);
        newEventDto = new EventDto(EVENT_NAME, FUTURE_EVENT_DATE, TICKETS_SALES_START_DATE, TICKETS_SALES_END_DATE, new ArrayList<>());
        createdEvent = new Event(1L, EVENT_NAME, FUTURE_EVENT_DATE, TICKETS_SALES_START_DATE, TICKETS_SALES_END_DATE, new ArrayList<>());
        duplicatedTicketTypes = new ArrayList<>();
        duplicatedTicketTypes.add(new AudienceTicket());
        duplicatedTicketTypes.add(new AudienceTicket());
    }


    @Test
    public void camposNomeEDataDoEventoDevemSerInformados() throws Exception{
        when(dtoToEntity.convert(any(EventDto.class))).thenReturn(createdEvent);
        when(repository.save(any(Event.class))).thenReturn(createdEvent);
        when(entityToDto.convert(any(Event.class))).thenReturn(newEventDto);
        EventDto result = service.save(newEventDto);
        assertEquals(createdEvent.getName(), result.getName());
        assertTrue(createdEvent.getDate().equals(result.getDate()));
    }

    @Test
    public void campoNomeNaoInformadoAoSalvar() throws Exception{
        createdEvent.setName(null);
        when(dtoToEntity.convert(any(EventDto.class))).thenReturn(createdEvent);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("O nome do evento é obrigatório");
        service.save(newEventDto);
    }

    @Test
    public void campoDataNaoInformadoAoSalvar() throws Exception{
        createdEvent.setDate(null);
        when(dtoToEntity.convert(any(EventDto.class))).thenReturn(createdEvent);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("A data do evento é obrigatória");
        service.save(newEventDto);
    }

    @Test
    public void campoNomeComMaisDe150Caracteres() throws Exception{
        createdEvent.setName(EVENT_NAME_151_CHARS);
        when(dtoToEntity.convert(any(EventDto.class))).thenReturn(createdEvent);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("O nome permite no máximo 150 caracteres");
        service.save(newEventDto);
    }

    @Test
    public void dataDoEventoNoPassado() throws Exception{
        createdEvent.setDate(PAST_EVENT_DATE);
        when(dtoToEntity.convert(any(EventDto.class))).thenReturn(createdEvent);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("A data do evento deve ser igual ou maior que a de hoje");
        service.save(newEventDto);
    }

    @Test
    public void dataDeInicioDeVendasDeIngressosNaoInformada() throws Exception {
        createdEvent.setTicketsSalesStartDateTime(null);
        when(dtoToEntity.convert(any(EventDto.class))).thenReturn(createdEvent);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Período de vendas é obrigatório");
        service.save(newEventDto);
    }

    @Test
    public void dataDeFimDeVendasDeIngressosNaoInformada() throws Exception {
        createdEvent.setTicketsSalesEndDateTime(null);
        when(dtoToEntity.convert(any(EventDto.class))).thenReturn(createdEvent);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Período de vendas é obrigatório");
        service.save(newEventDto);
    }

    @Test
    public void dataDeInicioEFimDeVendasDeIngressosNaoInformada() throws Exception {
        createdEvent.setTicketsSalesStartDateTime(null);
        when(dtoToEntity.convert(any(EventDto.class))).thenReturn(createdEvent);
        newEventDto.setTicketsSalesEndDateTime(null);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Período de vendas é obrigatório");
        service.save(newEventDto);
    }

    @Test
    public void dataDeInicioSuperiorDataDeFimDeVendasDeIngressos() throws Exception {
        createdEvent.setTicketsSalesStartDateTime(INVALID_TICKETS_SALES_START_DATE);
        when(dtoToEntity.convert(any(EventDto.class))).thenReturn(createdEvent);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("A date de início de venda deve ser inferior a date de fim");
        service.save(newEventDto);
    }

    @Test
    public void TiposDeIngressosDuplicados() throws Exception {
        createdEvent.setAvailableTicketTypes(duplicatedTicketTypes);
        when(dtoToEntity.convert(any(EventDto.class))).thenReturn(createdEvent);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("O evento possui tipos de ingressos duplicados");
        service.save(newEventDto);
    }

}
