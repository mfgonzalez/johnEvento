package br.edu.uniritter.evento.johnEvento.model;

public class VipAudienceTicket implements TicketType {
    @Override
    public String getName() {
        return "PLATEIA VIP";
    }

    @Override
    public Float getValue() {
        return new Float(500);
    }
}
