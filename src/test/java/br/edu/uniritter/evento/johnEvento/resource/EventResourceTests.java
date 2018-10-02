package br.edu.uniritter.evento.johnEvento.resource;

import br.edu.uniritter.evento.johnEvento.JohnEventoApplicationTests;
import br.edu.uniritter.evento.johnEvento.model.Event;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class EventResourceTests extends JohnEventoApplicationTests {

    @Test
    public void deve_incluir_evento() throws Exception {
        final LocalDateTime dataEvento = LocalDateTime.now().plusMonths(1);
        final Event evento = new Event(null, "name evento", dataEvento);
        RestAssured.given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(evento)
                .when()
                .post("/evento")
                .then()
                .log().headers()
                .log().body()
                .and()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", Matchers.equalTo("name evento"));
    }

    @Test
    public void deve_retornar_erro_nome_em_branco() throws Exception {
        final LocalDateTime dataEvento = LocalDateTime.now().plusMonths(1);
        final Event evento = new Event(null, null, dataEvento);
        RestAssured.given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(evento)
                .when()
                .post("/evento")
                .then()
                .log().headers()
                .log().body()
                .and()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(
                        "error", Matchers.equalTo("O name do evento é obrigatório")
                );
    }

    @Test
    public void deve_retornar_erro_nome_maior_150_caracteres() throws Exception {
        final LocalDateTime dataEvento = LocalDateTime.now().plusMonths(1);
        final String nomeEvento = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901";
        final Event evento = new Event(null, nomeEvento, dataEvento);
        RestAssured.given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(evento)
                .when()
                .post("/evento")
                .then()
                .log().headers()
                .log().body()
                .and()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(
                        "error", Matchers.equalTo("O name permite no máximo 150 caracteres")
                );
    }

    @Test
    public void deve_retornar_erro_data_nao_informada() throws Exception {
        final Event evento = new Event(null, "name evento", null);
        RestAssured.given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(evento)
                .when()
                .post("/evento")
                .then()
                .log().headers()
                .log().body()
                .and()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(
                        "error", Matchers.equalTo("A date do evento é obrigatória")
                );
    }

    @Test
    public void deve_retornar_erro_evento_data_passada() throws Exception {
        final LocalDateTime dataEvento = LocalDateTime.now().minusMonths(1);
        final Event evento = new Event(null, "name evento", dataEvento);
        RestAssured.given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(evento)
                .when()
                .post("/evento")
                .then()
                .log().headers()
                .log().body()
                .and()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(
                    "error", Matchers.equalTo("A date do evento deve ser igual ou maior que a de hoje")
                );
    }

}
