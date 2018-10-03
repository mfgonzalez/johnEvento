package br.edu.uniritter.evento.johnEvento.validator;

import br.edu.uniritter.evento.johnEvento.service.exception.InvalidFieldException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;

public class SalesDateValidatorTest {

    private static final LocalDateTime NULL_DATE_TIME = null;
    private static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2018, 1, 1, 0, 0);
    private static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2018, 2, 1, 0, 0);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void validarDataInicioVendasNula() throws Exception{
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Período de vendas é obrigatório");
        SalesDateValidator.validate(NULL_DATE_TIME, END_DATE_TIME);
    }

    @Test
    public void validarDataTerminoVendasNula() throws Exception{
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Período de vendas é obrigatório");
        SalesDateValidator.validate(START_DATE_TIME, NULL_DATE_TIME);
    }

    @Test
    public void validarDataInicioETerminoVendasNulas() throws Exception{
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("Período de vendas é obrigatório");
        SalesDateValidator.validate(NULL_DATE_TIME, NULL_DATE_TIME);
    }
}
