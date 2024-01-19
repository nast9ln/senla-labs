package ru.labs.coffer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.labs.coffer.dto.security.AuthenticationRequest;
import ru.labs.coffer.dto.security.AuthenticationResponse;
import ru.labs.coffer.dto.security.JwtPerson;

public interface AuthenticationController {
    ResponseEntity<AuthenticationResponse> register(JwtPerson request);
    ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request);
}
