package br.edu.uniritter.evento.johnEvento.validator;

import br.edu.uniritter.evento.johnEvento.model.*;
import br.edu.uniritter.evento.johnEvento.service.exception.InvalidFieldException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class TicketsValidatorTest {

    List<TicketType> ticketTypesOk;
    List<TicketType> duplicatedTicketTypes;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        ticketTypesOk = new ArrayList<>();
        ticketTypesOk.add(new AudienceTicket());
        ticketTypesOk.add(new VipAudienceTicket());
        duplicatedTicketTypes = new ArrayList<>();
        duplicatedTicketTypes.add(new AudienceTicket());
        duplicatedTicketTypes.add(new AudienceTicket());
    }

    @Test
    public void validarTiposDeIngressosOk() throws Exception{
        TicketsValidator.validate(ticketTypesOk);
        assertTrue("Não lançou exeception".equals("Não lançou exeception"));
    }

    @Test
    public void validarTiposDeIngressosDuplicados() throws Exception{
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("O evento possui tipos de ingressos duplicados");
        TicketsValidator.validate(duplicatedTicketTypes);
    }

}
