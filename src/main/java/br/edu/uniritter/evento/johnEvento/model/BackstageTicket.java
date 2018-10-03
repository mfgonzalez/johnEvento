package br.edu.uniritter.evento.johnEvento.model;

public class BackstageTicket implements TicketType {
    @Override
    public String getName() {
        return "BACKSTAGE";
    }

    @Override
    public Float getValue() {
        return new Float(800);
    }
}
