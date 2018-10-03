package br.edu.uniritter.evento.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, length=150)
    private String name;
    @Column(nullable=false)
    private LocalDateTime date;
    @Column(nullable=false)
    private LocalDateTime ticketsSalesStartDateTime;
    @Column(nullable=false)
    private LocalDateTime ticketsSalesEndDateTime;
    private List<TicketType> availableTicketTypes;
}
