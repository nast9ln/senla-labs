package com.example.demo.security;

import com.example.demo.entity.Person;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PersonDetailService implements UserDetailsService {
    private final PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personService.findByLogin(login);
        if (Objects.isNull(person)) {
            throw new EntityNotFoundException("Person with login " + login + " not found");
        }
        return buildUserDetails(person);
    }

    private UserDetails buildUserDetails(Person person) {
        return new User(person.getLogin(), person.getPassword(), null);
    }
}
