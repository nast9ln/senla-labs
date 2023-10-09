package org.example.main.service;

import org.example.main.dto.PersonDto;

public interface PersonService extends CRUD<PersonDto> {

    String execute(PersonDto dto);

}
