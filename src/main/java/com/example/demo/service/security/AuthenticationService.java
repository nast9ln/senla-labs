package com.example.demo.service.security;

import com.example.demo.dto.security.AuthenticationRequest;
import com.example.demo.dto.security.AuthenticationResponse;
import com.example.demo.dto.security.JwtPerson;
import com.example.demo.entity.Person;
import com.example.demo.entity.Role;
import com.example.demo.enums.RoleEnum;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.mapper.JwtPersonMapper;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtPersonMapper jwtPersonMapper;

    public AuthenticationResponse register(JwtPerson request) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(RoleEnum.ROLE_USER));
        Person person = Person.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(
                        request.getPassword()
                ))
                .roles(roles)
                .isDeleted(false)
                .build();
        try {
            person = personRepository.save(person);
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
        String token = jwtService.generateToken(jwtPersonMapper.toJwtPerson(person));
        return AuthenticationResponse
                .builder()
                .login(request.getLogin())
                .token(token)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );
        Person person = personRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new EntityNotFoundException("person not found with login: {0}", request.getLogin()));
        String token = jwtService.generateToken(jwtPersonMapper.toJwtPerson(person));
        return AuthenticationResponse.builder()
                .login(person.getLogin())
                .token(token)
                .build();
    }

}
