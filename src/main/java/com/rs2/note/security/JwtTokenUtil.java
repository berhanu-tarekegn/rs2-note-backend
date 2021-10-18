package com.rs2.note.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rs2.note.user.User;
import com.rs2.note.user.role.Role;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import java.util.Date;

import static java.lang.String.format;

@Component
public class JwtTokenUtil {

    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ISSUER = "rs2.com";

    @Value("${jwt.secret}")
    private String secret;

    public String parseTokenAndGetUsername(String token) {
        try {

            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject().split(",")[0];

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    public String generateToken(User user) {

        return Jwts.builder()
                .setSubject(format("%s,%s", user.getId(), user.getSurname()))
                .setIssuer(ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000)) // 1 week
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

   }

}
