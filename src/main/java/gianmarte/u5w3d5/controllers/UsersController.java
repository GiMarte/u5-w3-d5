package gianmarte.u5w3d5.controllers;

import gianmarte.u5w3d5.entities.User;
import gianmarte.u5w3d5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
}
