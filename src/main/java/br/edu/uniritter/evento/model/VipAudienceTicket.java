package br.edu.uniritter.evento.model;

public class VipAudienceTicket implements TicketType {
    @Override
    public String getName() {
        return "PLATEIA VIP";
    }

    @Override
    public Float getValue(DiscountGroup discountGroup) {
        return 500 * discountGroup.discount;
    }
}
