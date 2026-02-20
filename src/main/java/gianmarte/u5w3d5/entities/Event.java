package gianmarte.u5w3d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate localDate;
    private Integer seating;
    public Event(String title, String description, LocalDate localDate, Integer seating) {
    this.title = title;
    this.description = description;
    this.localDate = localDate;
    this.seating = seating;
}}
