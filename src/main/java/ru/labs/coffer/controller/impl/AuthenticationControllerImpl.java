package ru.labs.coffer.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.labs.coffer.controller.AuthenticationController;
import ru.labs.coffer.dto.security.AuthenticationRequest;
import ru.labs.coffer.dto.security.AuthenticationResponse;
import ru.labs.coffer.dto.security.JwtPerson;
import ru.labs.coffer.service.security.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationService authenticationService;

    @Override
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody JwtPerson request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
}