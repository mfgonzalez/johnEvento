package br.edu.uniritter.evento.model;

public class VipTicket implements TicketType {
    @Override
    public String getName() {
        return "VIP";
    }

    @Override
    public Float getValue() {
        return Float.valueOf(1000);
    }
}
