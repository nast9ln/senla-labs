package org.example.controller;

import org.example.dto.PersonDto;

public interface PersonController {

    PersonDto read(Long id);

    void update(PersonDto dto);

    void delete(Long id);

}
