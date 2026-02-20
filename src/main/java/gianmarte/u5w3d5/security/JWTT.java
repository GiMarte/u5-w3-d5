package gianmarte.u5w3d5.security;


import gianmarte.u5w3d5.entities.User;
import gianmarte.u5w3d5.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTT {
    @Value("${JWT_SECRET}")
    private String secret;

    public String generateToken(User user) {
        return Jwts.builder()
                   .issuedAt(new Date(System.currentTimeMillis()))
                   .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                   .subject(String.valueOf(user.getId()))
                   .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                   .compact();
    }

    public void verifyToken(String token) {

        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi col token! logga di nuovo");
        }
    }

    public Long extractIdFromToken(String token) {
        String subject = Jwts.parser()
                             .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                             .build()
                             .parseSignedClaims(token)
                             .getPayload()
                             .getSubject();

        return Long.valueOf(subject);
    }
}