package gianmarte.u5w3d5.services;

import gianmarte.u5w3d5.entities.User;
import gianmarte.u5w3d5.exceptions.UnauthorizedException;
import gianmarte.u5w3d5.payloads.LoginDTO;
import gianmarte.u5w3d5.security.JWTT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService usersService;
    private final JWTT jwtTools;
    private final PasswordEncoder bcrypt;

    @Autowired
    public AuthService(UserService usersService, JWTT jwtTools, PasswordEncoder bcrypt) {
    this.usersService = usersService;
    this.jwtTools = jwtTools;
    this.bcrypt = bcrypt;
}

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        User found = this.usersService.findByUsername(body.username());

        if (bcrypt.matches(body.password(), found.getPassword())) {
            String accessToken = jwtTools.generateToken(found);
            return accessToken;
        } else {
            throw new UnauthorizedException("credenziali sbagliate!");
        }



    }
}