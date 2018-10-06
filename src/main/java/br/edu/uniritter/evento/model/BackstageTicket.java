package br.edu.uniritter.evento.model;

public class BackstageTicket implements TicketType {
    @Override
    public String getName() {
        return "BACKSTAGE";
    }

    @Override
    public Float getValue(DiscountGroup discountGroup) {
        return 800 * discountGroup.discount;
    }
}
