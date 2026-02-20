package gianmarte.u5w3d5.repository;

import gianmarte.u5w3d5.entities.Event;
import gianmarte.u5w3d5.entities.Reservation;
import gianmarte.u5w3d5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByUserAndEvent(User user, Event event);

    long countByEvent(Event event);

    Page<Reservation> findByUser(User user, Pageable pageable);
}
