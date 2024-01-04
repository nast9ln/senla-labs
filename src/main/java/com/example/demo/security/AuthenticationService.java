package com.example.demo.security;

import com.example.demo.entity.Person;
import com.example.demo.entity.Role;
import com.example.demo.enums.RoleEnum;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private
    final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(JwtPerson request) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(RoleEnum.ROLE_USER));
        Person person = Person.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(
                        request.getPassword()
                ))
                .isDeleted(false)
                .build();
        try {
            personRepository.save(person);
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
        String token = jwtService.generateToken(request);
        return AuthenticationResponse
                .builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );
        Person person = personRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new EntityNotFoundException("person npt found with login: {0}", request.getLogin()));
        String token = jwtService.generateToken(request);
        return AuthenticationResponse.builder()
                .login(person.getLogin())
                .token(token)
                .build();
    }

}
