package org.example.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
public class TestJwtTokenProvider {
    private static final String PROFILE_ROLES = "roles";
    private static final String PROFILE_LOGIN = "login";

    @Value("${jwt.secret}")
    private String secret;

    private final Instant instancedAt = Instant.now();
    private final Instant expiresAt = Instant.now().plus(1, ChronoUnit.DAYS);

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String buildJwtToken(RoleEnum role) {
        return Jwts.builder()
                .setHeaderParam("iat", Date.from(instancedAt))
                .setHeaderParam("exp", Date.from(expiresAt))
                .claim(PROFILE_ROLES, role.name())
                .claim(PROFILE_LOGIN, "test_login")
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}