package ru.labs.coffer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.labs.coffer.dto.security.AuthenticationRequest;
import ru.labs.coffer.dto.security.AuthenticationResponse;
import ru.labs.coffer.dto.security.JwtPerson;

@Tag(name = "Контроллер аутентификации")
public interface AuthenticationController {
    @Operation(summary = "Регистрация пользователя")
    ResponseEntity<AuthenticationResponse> register(JwtPerson request);

    @Operation(summary = "Авторизация пользователя")
    ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request);
}
