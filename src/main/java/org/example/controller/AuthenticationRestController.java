package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AuthenticationRequestDto;
import org.example.entity.Person;
import org.example.security.JwtTokenProvider;
import org.example.service.PersonRegisterService;
import org.example.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PersonService personService;

    private final PersonRegisterService personRegisterService;

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequestDto requestDto) {
        String login = requestDto.getLogin();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, requestDto.getPassword()));
        Person person = personService.findByLogin(login);

        if (person == null) {
            throw new UsernameNotFoundException("User with login " + login + " not found");
        }
        String token = jwtTokenProvider.createToken(login, person.getRoles());

        Map<Object, Object> response = new HashMap<>();
        response.put("login", login);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody Person person) {
        log.info("Register person with login {}", person.getLogin());
        personRegisterService.register(person);
        return ResponseEntity.ok().build();
    }

}
