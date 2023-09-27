package org.example.service;

import org.example.dto.PersonDto;

public interface PersonService extends CRUD<PersonDto> {

    String execute(PersonDto dto);

}
