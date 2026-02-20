package gianmarte.u5w3d5.services;

import gianmarte.u5w3d5.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


}
