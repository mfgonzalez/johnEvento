package br.edu.uniritter.evento.model;

public class BackstageTicket implements TicketType {
    @Override
    public String getName() {
        return "BACKSTAGE";
    }

    @Override
    public Float getValue() {
        return Float.valueOf(800);
    }
}
