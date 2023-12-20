package org.example.service;

import org.example.dto.PersonDto;
import org.example.entity.Person;

public interface PersonService {
    PersonDto read(Long id);

    void update(PersonDto personDto);

    void delete(Long id);

    Person findByLogin(String login);
}
