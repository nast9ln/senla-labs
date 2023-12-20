package org.example.security;

import lombok.AllArgsConstructor;
import org.example.entity.Person;
import org.example.exception.EntityNotFoundException;
import org.example.service.PersonService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtUserDetailService implements UserDetailsService {
    private final PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Person person = personService.findByLogin(login);

        if (person == null) {
            throw new EntityNotFoundException("User with login " + login + " not found");
        }

        return JwtPersonFactory.create(person);
    }
}
