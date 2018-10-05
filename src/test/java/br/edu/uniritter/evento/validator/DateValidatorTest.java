package br.edu.uniritter.evento.validator;

import br.edu.uniritter.evento.service.exception.InvalidFieldException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
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
