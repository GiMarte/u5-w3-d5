package gianmarte.u5w3d5.services;

import gianmarte.u5w3d5.entities.User;
import gianmarte.u5w3d5.exceptions.NotFoundException;
import gianmarte.u5w3d5.exceptions.UnauthorizedException;
import gianmarte.u5w3d5.payloads.EventDTO;
import gianmarte.u5w3d5.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public gianmarte.u5w3d5.entities.Event save(gianmarte.u5w3d5.entities.Event event) {
        return eventRepository.save(event);
    }

    public org.springframework.data.domain.Page<gianmarte.u5w3d5.entities.Event> findAll(int page, int size) {
        if (size > 100 || size < 0)
            size = 10;
        if (page < 0)
            page = 0;
        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size);
        return eventRepository.findAll(pageable);
    }

    public gianmarte.u5w3d5.entities.Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new gianmarte.u5w3d5.exceptions.NotFoundException(id));
    }

    public gianmarte.u5w3d5.entities.Event updateEvent(Long id, EventDTO payload, User currentUser) {
        gianmarte.u5w3d5.entities.Event found = this.findById(id);
        if (!found.getOrganizer().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("Non puoi modificare un evento che non hai creato.");
        }
        found.setTitle(payload.title());
        found.setDescription(payload.description());
        found.setLocalDate(payload.localDate());
        found.setLocation(payload.location());
        found.setSeating(payload.seating());
        return this.save(found);
    }

    public void delete(Long id, User currentUser) {
        gianmarte.u5w3d5.entities.Event event = findById(id);
        if (!event.getOrganizer().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException("Non puoi eliminare un evento che non hai creato.");
        }
        eventRepository.delete(event);
    }
}
