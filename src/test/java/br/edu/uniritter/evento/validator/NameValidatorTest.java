package br.edu.uniritter.evento.validator;

import br.edu.uniritter.evento.service.exception.InvalidFieldException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class NameValidatorTest {

    private static final String EMPTY_NAME = "";
    private static final String NULL_NAME = "";
    private static final String NAME_151_CHARS = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void validarNomeVazio() throws Exception{
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("O nome do evento é obrigatório");
        NameValidator.validate(EMPTY_NAME);
    }

    @Test
    public void validarNomeNulo() throws Exception{
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("O nome do evento é obrigatório");
        NameValidator.validate(NULL_NAME);
    }

    @Test
    public void validarNomeComMaisDe150Caracteres() throws Exception{
        expectedException.expect(InvalidFieldException.class);
        expectedException.expectMessage("O nome permite no máximo 150 caracteres");
        NameValidator.validate(NAME_151_CHARS);
    }

}
