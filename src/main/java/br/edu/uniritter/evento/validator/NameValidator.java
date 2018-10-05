package br.edu.uniritter.evento.validator;

import br.edu.uniritter.evento.service.exception.InvalidFieldException;

public class NameValidator {
    public static void validate(String name) throws InvalidFieldException {
        if (name == null || name.isEmpty()) {
            throw new InvalidFieldException("O nome do evento é obrigatório");
        }
        if (name.length() > 150) {
            throw new InvalidFieldException("O nome permite no máximo 150 caracteres");
        }
    }
}
