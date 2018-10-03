package br.edu.uniritter.evento.johnEvento.model;

public class TicketFactory {

    public TicketType getTicketType(String type) {
        if (type.equalsIgnoreCase("VIP")) {
            return new VipTicket();
        } else if (type.equalsIgnoreCase("BACKSTAGE")) {
            return new BackstageTicket();
        } else if (type.equalsIgnoreCase("PLATEIA VIP")) {
            return new VipAudienceTicket();
        } else if (type.equalsIgnoreCase("PLATEIA")) {
            return new AudienceTicket();
        }
        return null;
    }
}
