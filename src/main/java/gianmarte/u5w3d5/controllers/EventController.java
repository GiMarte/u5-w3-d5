package gianmarte.u5w3d5.controllers;

import gianmarte.u5w3d5.entities.Event;
import gianmarte.u5w3d5.exceptions.ValidationException;
import gianmarte.u5w3d5.entities.User;
import gianmarte.u5w3d5.payloads.EventDTO;
import gianmarte.u5w3d5.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public Event createEvent(@RequestBody @Validated EventDTO payload, BindingResult validationResult, @AuthenticationPrincipal User currentUser) {
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException(errorsList);
        } else {
            Event newEvent = new Event(payload.title(), payload.description(), payload.localDate(), payload.location(), payload.seating(), currentUser);
            return this.eventService.save(newEvent);
        }
    }

    @GetMapping
    public Page<Event> findAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return this.eventService.findAll(page, size);
    }

    @GetMapping("/{eventId}")
    public Event findById(@PathVariable Long eventId) {
        return this.eventService.findById(eventId);
    }

    @PutMapping("/{eventId}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public Event updateEvent(@PathVariable Long eventId, @RequestBody @Validated EventDTO payload,
            BindingResult validationResult, @AuthenticationPrincipal User currentUser) {
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException(errorsList);
        }
        return this.eventService.updateEvent(eventId, payload, currentUser);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public void deleteEvent(@PathVariable Long eventId, @AuthenticationPrincipal User currentUser) {
        this.eventService.delete(eventId, currentUser);
    }
}
