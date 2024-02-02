package ru.labs.coffer.util;

import ru.labs.coffer.dto.security.JwtPerson;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.mapper.JwtPersonMapper;
import ru.labs.coffer.repository.PersonRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
public class TestJwtTokenProvider {
    private static final String PROFILE_ROLES = "authorities";
    private static final String PROFILE_LOGIN = "login";
    private static final String PROFILE_ID = "id";

    @Value("${jwt.secret}")
    private String secret;

    private final Instant instancedAt = Instant.now();
    private final Instant expiresAt = Instant.now().plus(1, ChronoUnit.DAYS);

    @Autowired
    private PersonRepository repository;
    @Autowired
    private JwtPersonMapper mapper;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String buildJwtToken(String login) {
        Person person = repository.findByLogin(login).orElseThrow();
        JwtPerson jwtPerson = mapper.toJwtPerson(person);
        return MessageFormat.format("Bearer {0}", Jwts.builder()
                .setSubject(jwtPerson.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 100))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .claim(PROFILE_ID, jwtPerson.getId())
                .claim(PROFILE_LOGIN, jwtPerson.getLogin())
                .claim(PROFILE_ROLES, jwtPerson.getAuthorities())
                .compact());
    }


    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
