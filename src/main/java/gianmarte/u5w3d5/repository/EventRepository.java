package gianmarte.u5w3d5.repository;

import gianmarte.u5w3d5.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
