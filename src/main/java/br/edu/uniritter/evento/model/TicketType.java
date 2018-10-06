package br.edu.uniritter.evento.model;

public interface TicketType {
    String getName();
    Float getValue(DiscountGroup discountGroup);
}
