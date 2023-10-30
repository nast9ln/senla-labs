package org.example.controller;

import org.example.dto.PersonDto;

public interface PersonController {


    PersonDto create(PersonDto dto);

    PersonDto read(Long id);

    PersonDto update(PersonDto dto);

    void delete(Long id);

}
