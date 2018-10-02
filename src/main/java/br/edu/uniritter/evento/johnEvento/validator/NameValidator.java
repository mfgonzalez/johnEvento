package br.edu.uniritter.evento.johnEvento.validator;

import br.edu.uniritter.evento.johnEvento.service.exception.InvalidFieldException;

public class NameValidator {
    public static void validate(String name) throws InvalidFieldException {
        if (name == null || name.isEmpty()) {
            throw new InvalidFieldException("O name do evento é obrigatório");
        }
        if (name.length() > 150) {
            throw new InvalidFieldException("O name permite no máximo 150 caracteres");
        }
    }
}
