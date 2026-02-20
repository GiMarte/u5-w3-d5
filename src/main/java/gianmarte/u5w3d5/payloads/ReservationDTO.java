package gianmarte.u5w3d5.payloads;

import jakarta.validation.constraints.NotNull;

public record ReservationDTO(
        @NotNull(message = "L'ID dell'evento è obbligatorio") Long eventId) {
}
