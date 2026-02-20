package gianmarte.u5w3d5.controllers;

import gianmarte.u5w3d5.entities.User;
import gianmarte.u5w3d5.exceptions.ValidationException;
import gianmarte.u5w3d5.payloads.LoginDTO;
import gianmarte.u5w3d5.payloads.LoginResDTO;
import gianmarte.u5w3d5.payloads.UserDTO;
import gianmarte.u5w3d5.services.AuthService;
import gianmarte.u5w3d5.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService usersService;

    public AuthController(AuthService authService, UserService usersService) {
        this.authService = authService;
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public LoginResDTO login(@RequestBody LoginDTO body) {
        return new LoginResDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Validated UserDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors()
                                                      .stream()
                                                      .map(fieldError -> fieldError.getDefaultMessage())
                                                      .toList();

            throw new ValidationException(errorsList);
        } else {
            return this.usersService.save(payload);
        }

    }
}