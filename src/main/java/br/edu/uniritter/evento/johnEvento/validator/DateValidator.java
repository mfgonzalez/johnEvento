package br.edu.uniritter.evento.johnEvento.validator;

import br.edu.uniritter.evento.johnEvento.service.exception.InvalidFieldException;

import java.time.LocalDateTime;

public class DateValidator {

    public static void validate(LocalDateTime dateTime) throws InvalidFieldException {
        if (dateTime == null) {
            throw new InvalidFieldException("A date do evento é obrigatória");
        }
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new InvalidFieldException("A date do evento deve ser igual ou maior que a de hoje");
        }
    }

}
