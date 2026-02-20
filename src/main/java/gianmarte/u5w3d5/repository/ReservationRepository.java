package gianmarte.u5w3d5.repository;

import gianmarte.u5w3d5.entities.Event;
import gianmarte.u5w3d5.entities.Reservation;
import gianmarte.u5w3d5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByUserAndEvent(User user, Event event);

    long countByEvent(Event event);
}
