package br.edu.uniritter.evento.resource;

import br.edu.uniritter.evento.model.ErrorWrapper;
import br.edu.uniritter.evento.model.Event;
import br.edu.uniritter.evento.service.EventService;
import br.edu.uniritter.evento.service.exception.InvalidFieldException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evento")
public class EventResource {

    private EventService service;

    @Autowired
    public EventResource(EventService service) {
        this.service = service;
    }

    @ApiOperation(value = "Inclusão de novos eventos", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Evento criado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<Event> add(@RequestBody Event event) throws InvalidFieldException {
        Event newEvent = service.save(event);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ErrorWrapper> handleInvalidFieldException(InvalidFieldException e) {
        return new ResponseEntity<>(new ErrorWrapper(e.getMessage()), HttpStatus.BAD_REQUEST);
    }    

}