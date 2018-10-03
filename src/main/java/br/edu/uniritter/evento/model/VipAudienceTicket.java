package br.edu.uniritter.evento.model;

public class VipAudienceTicket implements TicketType {
    @Override
    public String getName() {
        return "PLATEIA VIP";
    }

    @Override
    public Float getValue() {
        return Float.valueOf(500);
    }
}
