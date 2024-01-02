package com.example.demo.service;

import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;

public interface PersonService {
    PersonDto read(Long id);

    void update(PersonDto personDto);

    void delete(Long id);

    Person findByLogin(String login);
}