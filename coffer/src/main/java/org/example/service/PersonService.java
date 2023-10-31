package org.example.service;

import org.example.dto.PersonDto;

public interface PersonService {

    void create(PersonDto dto);

    PersonDto read(Long id);

    void update(PersonDto dto);

    void delete(Long id);

}
