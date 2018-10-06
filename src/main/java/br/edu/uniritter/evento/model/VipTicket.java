package br.edu.uniritter.evento.model;

public class VipTicket implements TicketType {
    @Override
    public String getName() {
        return "VIP";
    }

    @Override
    public Float getValue(DiscountGroup discountGroup) {
        return 1000 * discountGroup.discount;
    }
}
