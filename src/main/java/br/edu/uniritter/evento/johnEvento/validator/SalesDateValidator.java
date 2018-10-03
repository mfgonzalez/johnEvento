package br.edu.uniritter.evento.johnEvento.validator;

import br.edu.uniritter.evento.johnEvento.service.exception.InvalidFieldException;

import java.time.LocalDateTime;

public class SalesDateValidator {

    public static void validate(LocalDateTime salesStartDate, LocalDateTime salesEndDate) throws InvalidFieldException {
        if (salesStartDate == null || salesEndDate == null) {
            throw new InvalidFieldException("Período de vendas é obrigatório");
        }
        if (salesStartDate.isAfter(salesEndDate)) {
            throw new InvalidFieldException("A date de início de venda deve ser inferior a date de fim");
        }
    }

}
