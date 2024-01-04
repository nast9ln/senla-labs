package com.example.demo.controller;

import com.example.demo.dto.PersonDto;

public interface PersonController {

    PersonDto read(Long id);

    void update(PersonDto dto);

    void delete(Long id);
}
