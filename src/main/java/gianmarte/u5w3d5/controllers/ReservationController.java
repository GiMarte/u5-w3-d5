package gianmarte.u5w3d5.controllers;

import gianmarte.u5w3d5.entities.Reservation;
import gianmarte.u5w3d5.entities.User;
import gianmarte.u5w3d5.exceptions.ValidationException;
import gianmarte.u5w3d5.payloads.ReservationDTO;
import gianmarte.u5w3d5.services.ReservationService;
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
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@RequestBody @Validated ReservationDTO payload,
            BindingResult validationResult,
            @AuthenticationPrincipal User currentUser) {
        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            throw new ValidationException(errorsList);
        }
        return this.reservationService.createReservation(payload, currentUser);
    }

    @GetMapping("/me")
    public Page<Reservation> getMyReservations(@AuthenticationPrincipal User currentUser,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return this.reservationService.findMyReservations(currentUser, page, size);
    }

    @DeleteMapping("/{reservationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable Long reservationId, @AuthenticationPrincipal User currentUser) {
        this.reservationService.delete(reservationId, currentUser);
    }
}
