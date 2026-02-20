package gianmarte.u5w3d5.payloads;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    @NotBlank
    String username;
    @NotBlank
    String password;
    @NotBlank
    String name;
    @NotBlank
    String surname;
}
