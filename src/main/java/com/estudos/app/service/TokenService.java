package com.estudos.app.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("123456789")
    private String secret;

    public String generateToken(String email, Authentication authentication){
        Algorithm algorithm = encryptor(secret);
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(Date.from(this.generateExpirationDate()))
                .sign(algorithm);
    }

    public String validateToken(String token){
        Algorithm algorithm = encryptor(secret);
        try{
            return JWT.require(algorithm)
                    .build()
                    .verify(token).getToken();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }

    public Algorithm encryptor(String jwtSecret){
        return Algorithm.HMAC256(jwtSecret);
    }
    public String getEmailFromToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }
}
