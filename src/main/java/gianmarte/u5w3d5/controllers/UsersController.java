package gianmarte.u5w3d5.controllers;

import gianmarte.u5w3d5.entities.User;
import gianmarte.u5w3d5.payloads.UserDTO;
import gianmarte.u5w3d5.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    @Autowired
    public UsersController(UserService userService) {
    this.userService = userService;
}
    @GetMapping
    public Page<User> findAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size) {
        return this.userService.findAll(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@Valid @RequestBody UserDTO payload) {
        User salvato = userService.save(payload);
        return new UserDTO(salvato.getUsername(), salvato.getPassword())
        ;
    }

}
