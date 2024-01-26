package ru.labs.coffer.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.labs.coffer.dto.security.AuthenticationRequest;
import ru.labs.coffer.dto.security.AuthenticationResponse;
import ru.labs.coffer.dto.security.JwtPerson;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.entity.Role;
import ru.labs.coffer.enums.RoleEnum;
import ru.labs.coffer.exception.BaseException;
import ru.labs.coffer.exception.EntityNotFoundException;
import ru.labs.coffer.exception.LoginDuplicateException;
import ru.labs.coffer.mapper.JwtPersonMapper;
import ru.labs.coffer.repository.PersonRepository;
import ru.labs.coffer.repository.RoleRepository;

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
        } catch (Exception e) {
            throw new LoginDuplicateException("The username is already taken: {0}", person.getLogin());
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
