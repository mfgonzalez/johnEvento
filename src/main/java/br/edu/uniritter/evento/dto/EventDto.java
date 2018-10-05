package br.edu.uniritter.evento.dto;

import br.edu.uniritter.evento.model.TicketType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EventDto {
    private String name;
    private LocalDateTime date;
    private LocalDateTime ticketsSalesStartDateTime;
    private LocalDateTime ticketsSalesEndDateTime;
    private List<TicketType> availableTicketTypes;
}
