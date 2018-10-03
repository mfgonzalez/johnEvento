package br.edu.uniritter.evento.johnEvento.validator;

import br.edu.uniritter.evento.johnEvento.service.exception.InvalidFieldException;
import br.edu.uniritter.evento.johnEvento.validator.DateValidator;
import br.edu.uniritter.evento.johnEvento.validator.NameValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;

public class DateValidatorTest {

    private static final LocalDateTime NULL_DATE_TIME = null;
    private static final LocalDateTime PAST_DATE_TIME = LocalDateTime.of(2000, 1, 1, 0, 0);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void validarDataNula() throws Exception{
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("A data do evento é obrigatória");
        DateValidator.validate(NULL_DATE_TIME);
    }

    @Test
    public void validarDataPassada() throws Exception{
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("A data do evento deve ser igual ou maior que a de hoje");
        DateValidator.validate(PAST_DATE_TIME);
    }
}
