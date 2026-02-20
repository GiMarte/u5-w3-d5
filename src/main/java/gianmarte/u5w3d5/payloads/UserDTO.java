package gianmarte.u5w3d5.payloads;

import gianmarte.u5w3d5.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserDTO(
        @NotBlank
        String username,
        @NotBlank
        @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{4,}$")
        String password,
        @NotNull
        Role role
) {
}
