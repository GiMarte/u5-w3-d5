package gianmarte.u5w3d5.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record EventDTO(
        @NotEmpty(message = "Il titolo è obbligatorio") String title,
        @NotEmpty(message = "La descrizione è obbligatoria") String description,
        @NotNull(message = "La data è obbligatoria") @Future(message = "La data dell'evento deve essere nel futuro") LocalDate localDate,
        @NotEmpty(message = "Il luogo è obbligatorio") String location,
        @NotNull(message = "Il numero di posti è obbligatorio") @Min(value = 1, message = "L'evento deve avere almeno 1 posto") Integer seating) {
}
