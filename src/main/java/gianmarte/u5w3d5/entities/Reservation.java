package gianmarte.u5w3d5.entities;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

}
