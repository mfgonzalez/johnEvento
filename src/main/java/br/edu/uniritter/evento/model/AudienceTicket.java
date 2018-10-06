package br.edu.uniritter.evento.model;

public class AudienceTicket implements TicketType {
    @Override
    public String getName() {
        return "PLATEIA";
    }

    @Override
    public Float getValue(DiscountGroup discountGroup) {
        return 300 * discountGroup.discount;
    }
}
