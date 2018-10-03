package br.edu.uniritter.evento.johnEvento.model;

public class AudienceTicket implements TicketType {
    @Override
    public String getName() {
        return "PLATEIA";
    }

    @Override
    public Float getValue() {
        return new Float(300);
    }
}
