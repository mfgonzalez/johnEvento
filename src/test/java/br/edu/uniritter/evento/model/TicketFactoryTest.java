package br.edu.uniritter.evento.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class TicketFactoryTest {

    TicketFactory ticketFactory;

    @Before
    public void setup() {
        ticketFactory = new TicketFactory();
    }

    @Test
    public void validaIngressoVip() {
        TicketType ticketType = ticketFactory.getTicketType("VIP");
        assertEquals("VIP", ticketType.getName());
        assertEquals(new Float(1000), ticketType.getValue(DiscountGroup.GENERAL));
    }

    @Test
    public void validaIngressoBackstage() {
        TicketType ticketType = ticketFactory.getTicketType("BACKSTAGE");
        assertEquals("BACKSTAGE", ticketType.getName());
        assertEquals(new Float(800), ticketType.getValue(DiscountGroup.GENERAL));
    }

    @Test
    public void validaIngressoPlateiaVip() {
        TicketType ticketType = ticketFactory.getTicketType("PLATEIA VIP");
        assertEquals("PLATEIA VIP", ticketType.getName());
        assertEquals(new Float(500), ticketType.getValue(DiscountGroup.GENERAL));
    }

    @Test
    public void validaIngressoPlateia() {
        TicketType ticketType = ticketFactory.getTicketType("PLATEIA");
        assertEquals("PLATEIA", ticketType.getName());
        assertEquals(Float.valueOf(300), ticketType.getValue(DiscountGroup.GENERAL));
    }

    @Test
    public void validaIngressoNaoExistente() {
        TicketType ticketType = ticketFactory.getTicketType("NAO EXISTE");
        assertNull(ticketType);
    }

    @Test
    public void validaDescontoGold() {
        TicketType ticketType = ticketFactory.getTicketType("VIP");
        assertEquals("VIP", ticketType.getName());
        assertEquals(new Float(750), ticketType.getValue(DiscountGroup.GOLD));
    }

    @Test
    public void validaDescontoSilver() {
        TicketType ticketType = ticketFactory.getTicketType("VIP");
        assertEquals("VIP", ticketType.getName());
        assertEquals(new Float(600), ticketType.getValue(DiscountGroup.SILVER));
    }

    @Test
    public void validaDescontoIdoso() {
        TicketType ticketType = ticketFactory.getTicketType("VIP");
        assertEquals("VIP", ticketType.getName());
        assertEquals(new Float(500), ticketType.getValue(DiscountGroup.ELDER));
    }

    @Test
    public void validaDescontoEstudante() {
        TicketType ticketType = ticketFactory.getTicketType("VIP");
        assertEquals("VIP", ticketType.getName());
        assertEquals(new Float(500), ticketType.getValue(DiscountGroup.STUDENT));
    }

    @Test
    public void validaDescontoNormal() {
        TicketType ticketType = ticketFactory.getTicketType("VIP");
        assertEquals("VIP", ticketType.getName());
        assertEquals(new Float(1000), ticketType.getValue(DiscountGroup.GENERAL));
    }

}
