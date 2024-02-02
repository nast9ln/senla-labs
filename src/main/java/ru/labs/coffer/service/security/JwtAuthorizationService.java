package ru.labs.coffer.service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.labs.coffer.dto.security.JwtPerson;
import ru.labs.coffer.exception.JwtParsingException;

import java.util.Optional;

@Service
public class JwtAuthorizationService {

    public JwtPerson extractJwtPerson() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal).map(JwtPerson.class::cast)
                .orElseThrow(() -> new JwtParsingException("Exception while deserializing token"));
    }

}
