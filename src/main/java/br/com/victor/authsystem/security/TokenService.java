package br.com.victor.authsystem.security;

import br.com.victor.authsystem.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final String ISSUER = "Auth System";

    @Value("${api.secret-key}")
    private String secret;

    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId())
                    .withExpiresAt(getExpiresAt())
                    .sign(algorithm);
        }
        catch (JWTCreationException e) {
            throw new RuntimeException("Error generating token", e);
        }
    }

    public String verifyToken(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid token!");
        }
    }

    private Instant getExpiresAt() {
        return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public boolean validatePassword(String password, String passwordCrypt) {
        return BCrypt.checkpw(password, passwordCrypt);
    }
}
