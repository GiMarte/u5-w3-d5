package gianmarte.u5w3d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate localDate;
    private String location;
    private Integer seating;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    public Event(String title, String description, LocalDate localDate, String location, Integer seating, User organizer) {
        this.title = title;
        this.description = description;
        this.localDate = localDate;
        this.location = location;
        this.seating = seating;
        this.organizer = organizer;
    }
}
