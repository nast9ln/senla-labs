package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.PersonDto;
import org.example.service.impl.PersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PersonControllerImpl implements PersonController {
    private final PersonServiceImpl personService;
    private static final Logger logger = LoggerFactory.getLogger(PersonControllerImpl.class);

    @Override
    public PersonDto create(PersonDto dto) {
        logger.info("create");
        return personService.create(dto);
    }

    @Override
    public PersonDto read(Long id) {
        logger.info("read");
        return personService.read(id);
    }

    @Override
    public void update(PersonDto dto) {
        logger.info("update");
        personService.update(dto);
    }

    @Override
    public void delete(Long id) {
        logger.info("delete");
        personService.delete(id);
    }
}