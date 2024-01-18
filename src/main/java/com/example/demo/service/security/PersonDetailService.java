package com.example.demo.service.security;

import com.example.demo.dto.security.JwtPerson;
import com.example.demo.entity.Person;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.mapper.JwtPersonMapper;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PersonDetailService implements UserDetailsService {
    private final PersonService personService;
    private final JwtPersonMapper personMapper;

    @Override
    public JwtPerson loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personService.findByLogin(login);
        if (Objects.isNull(person)) {
            throw new EntityNotFoundException("Person with login " + login + " not found");
        }
        return buildUserDetails(person);
    }

    private JwtPerson buildUserDetails(Person person) {
        return personMapper.toJwtPerson(person);
    }
}
