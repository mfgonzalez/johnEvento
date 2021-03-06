package br.edu.uniritter.evento.validator;

import br.edu.uniritter.evento.model.TicketType;
import br.edu.uniritter.evento.service.exception.InvalidFieldException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketsValidator {
    private TicketsValidator() {
        throw new IllegalStateException("Utility class");
    }
    public static void validate(List<TicketType> ticketTypes) throws InvalidFieldException {
        if(containsDuplicated(ticketTypes)) {
            throw new InvalidFieldException("O evento possui tipos de ingressos duplicados");
        }
    }
    private static boolean containsDuplicated(List<TicketType> ticketTypes) {
        if (ticketTypes != null) {
            Set<String> set = new HashSet<>();
            for (TicketType t : ticketTypes) {
                if (!set.add(t.getName()))
                    return true;
            }
        }
        return false;
    }
}
