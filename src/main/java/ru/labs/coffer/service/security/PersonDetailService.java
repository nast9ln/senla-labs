package ru.labs.coffer.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.labs.coffer.dto.security.JwtPerson;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.exception.EntityNotFoundException;
import ru.labs.coffer.mapper.JwtPersonMapper;
import ru.labs.coffer.service.PersonService;

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
