package br.edu.uniritter.evento.johnEvento.service;

import br.edu.uniritter.evento.johnEvento.model.Event;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EventServiceImplTest {

    private static final String NOME_EVENTO = "Evento Teste";
    private static final String NOME_EVENTO_151_CARACTERES = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901";
    private static final LocalDateTime DATA_EVENTO_FUTURO = LocalDateTime.of(2018, 10, 30, 9, 30);
    private static final LocalDateTime DATA_EVENTO_PASSADO = LocalDateTime.of(2017, 10, 30, 9, 30);
    private static final LocalDateTime DATA_EVENTO_ATUAL = LocalDateTime.of(2018, 9, 1, 9, 30);

    private EventService service;

    private Event newEvent;
    private Event createdEvent;

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
        newEvent = new Event(null, NOME_EVENTO, DATA_EVENTO_FUTURO);
        createdEvent = new Event(1L, NOME_EVENTO, DATA_EVENTO_FUTURO);
    }

    @Test
    public void campos_nome_e_data_do_evento_devem_ser_informados_ok() throws Exception{
        when(repository.save(newEvent)).thenReturn(createdEvent);
        Event result = service.save(newEvent);
        assertEquals(newEvent.getName(), result.getName());
        assertTrue(newEvent.getDate().equals(result.getDate()));
    }

    @Test
    public void campo_nome_nao_informados_ao_salvar() throws Exception{
        newEvent.setName(null);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("O name do evento é obrigatório");
        service.save(newEvent);
    }

    @Test
    public void campo_data_nao_informados_ao_salvar() throws Exception{
        newEvent.setDate(null);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("A date do evento é obrigatória");
        service.save(newEvent);
    }

    @Test
    public void campo_nome_com_mais_de_150_caracteres() throws Exception{
        newEvent.setName(NOME_EVENTO_151_CARACTERES);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("O name permite no máximo 150 caracteres");
        service.save(newEvent);
    }

    @Test
    public void data_do_evento_no_passado() throws Exception{
        newEvent.setDate(DATA_EVENTO_PASSADO);
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("A date do evento deve ser igual ou maior que a de hoje");
        service.save(newEvent);
    }

}
