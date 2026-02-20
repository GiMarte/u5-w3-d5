package gianmarte.u5w3d5.services;

import gianmarte.u5w3d5.entities.Event;
import gianmarte.u5w3d5.entities.Reservation;
import gianmarte.u5w3d5.entities.User;
import gianmarte.u5w3d5.exceptions.BadRequestException;
import gianmarte.u5w3d5.exceptions.NotFoundException;
import gianmarte.u5w3d5.payloads.ReservationDTO;
import gianmarte.u5w3d5.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final EventService eventService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, EventService eventService) {
        this.reservationRepository = reservationRepository;
        this.eventService = eventService;
    }

    public Reservation createReservation(ReservationDTO payload, User user) {
        Event event = eventService.findById(payload.eventId());

        if (reservationRepository.existsByUserAndEvent(user, event)) {
            throw new BadRequestException("Hai già prenotato per questo evento!");
        }

        if (reservationRepository.countByEvent(event) >= event.getSeating()) {
            throw new BadRequestException("L'evento ha raggiunto la capienza massima (Sold Out)!");
        }

        Reservation newReservation = new Reservation(event, user);
        return reservationRepository.save(newReservation);
    }

    public Page<Reservation> findMyReservations(User user, int page, int size) {
        if (size > 100 || size < 0)
            size = 10;
        if (page < 0)
            page = 0;
        Pageable pageable = PageRequest.of(page, size);
        return reservationRepository.findByUser(user, pageable);
    }

    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(Long id, User user) {
        Reservation reservation = findById(id);
        if (!reservation.getUser().getId().equals(user.getId()) && !user.getRole().name().equals("ORGANIZER")) {
            throw new BadRequestException("Non puoi cancellare la prenotazione di un altro utente.");
        }
        reservationRepository.delete(reservation);
    }
}
