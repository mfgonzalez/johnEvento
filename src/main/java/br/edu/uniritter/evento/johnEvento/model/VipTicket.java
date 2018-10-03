package br.edu.uniritter.evento.johnEvento.model;

public class VipTicket implements TicketType {
    @Override
    public String getName() {
        return "VIP";
    }

    @Override
    public Float getValue() {
        return new Float(1000);
    }
}
