package br.edu.uniritter.evento.johnEvento.service;

import br.edu.uniritter.evento.johnEvento.model.AudienceTicket;
import br.edu.uniritter.evento.johnEvento.model.Event;
import br.edu.uniritter.evento.johnEvento.model.TicketType;
import br.edu.uniritter.evento.johnEvento.repository.EventRepository;
import br.edu.uniritter.evento.johnEvento.service.exception.InvalidFieldException;
import br.edu.uniritter.evento.johnEvento.service.impl.EventServiceImpl;
import br.edu.uniritter.evento.johnEvento.validator.DateValidator;
import br.edu.uniritter.evento.johnEvento.validator.NameValidator;
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

    private Event newEvent;
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

    @Before
    public void setup() {
        service = new EventServiceImpl(repository, nameValidator, dateValidator);
        newEvent = new Event(null, EVENT_NAME, FUTURE_EVENT_DATE, TICKETS_SALES_START_DATE, TICKETS_SALES_END_DATE, new ArrayList<>());
        createdEvent = new Event(1L, EVENT_NAME, FUTURE_EVENT_DATE, TICKETS_SALES_START_DATE, TICKETS_SALES_END_DATE, new ArrayList<>());
        duplicatedTicketTypes = new ArrayList<>();
        duplicatedTicketTypes.add(new AudienceTicket());
        duplicatedTicketTypes.add(new AudienceTicket());
    }

    @Test
    public void camposNomeEDataDoEventoDevemSerInformados() throws Exception{
        when(repository.save(newEvent)).thenReturn(createdEvent);
        Event result = service.save(newEvent);
        assertEquals(newEvent.getName(), result.getName());
        assertTrue(newEvent.getDate().equals(result.getDate()));
    }

    @Test
    public void campoNomeNaoInformadoAoSalvar() throws Exception{
        newEvent.setName(null);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("O nome do evento é obrigatório");
        service.save(newEvent);
    }

    @Test
    public void campoDataNaoInformadoAoSalvar() throws Exception{
        newEvent.setDate(null);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("A data do evento é obrigatória");
        service.save(newEvent);
    }

    @Test
    public void campoNomeComMaisDe150Caracteres() throws Exception{
        newEvent.setName(EVENT_NAME_151_CHARS);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("O nome permite no máximo 150 caracteres");
        service.save(newEvent);
    }

    @Test
    public void dataDoEventoNoPassado() throws Exception{
        newEvent.setDate(PAST_EVENT_DATE);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("A data do evento deve ser igual ou maior que a de hoje");
        service.save(newEvent);
    }

    @Test
    public void dataDeInicioDeVendasDeIngressosNaoInformada() throws Exception {
        newEvent.setTicketsSalesStartDateTime(null);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Período de vendas é obrigatório");
        service.save(newEvent);
    }

    @Test
    public void dataDeFimDeVendasDeIngressosNaoInformada() throws Exception {
        newEvent.setTicketsSalesEndDateTime(null);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Período de vendas é obrigatório");
        service.save(newEvent);
    }

    @Test
    public void dataDeInicioEFimDeVendasDeIngressosNaoInformada() throws Exception {
        newEvent.setTicketsSalesStartDateTime(null);
        newEvent.setTicketsSalesEndDateTime(null);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Período de vendas é obrigatório");
        service.save(newEvent);
    }

    @Test
    public void dataDeInicioSuperiorDataDeFimDeVendasDeIngressos() throws Exception {
        newEvent.setTicketsSalesStartDateTime(INVALID_TICKETS_SALES_START_DATE);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("A date de início de venda deve ser inferior a date de fim");
        service.save(newEvent);
    }

    @Test
    public void TiposDeIngressosDuplicados() throws Exception {
        newEvent.setAvailableTicketTypes(duplicatedTicketTypes);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("O evento possui tipos de ingressos duplicados");
        service.save(newEvent);
    }

}
