package org.example.main.controller;

import org.example.di.annotation.Autowired;
import org.example.di.annotation.Component;
import org.example.main.dto.PersonDto;
import org.example.main.service.CRUD;
import org.example.main.service.impl.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component

public class PersonController implements CRUD<PersonDto> {
    private static PersonService personService;
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);


    public PersonController() {

    }

    @Autowired
    public PersonController(PersonService personService) {
        PersonController.personService = personService;
    }

    public org.example.main.service.PersonService getPersonService() {
        return personService;
    }

    public String execute(PersonDto dto) {
        logger.info("execute");
        return personService.execute(dto);
    }

    @Override
    public void create(PersonDto dto) {
        logger.info("create");
        personService.create(dto);
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


    public void setPersonService(PersonService personService) {
        PersonController.personService = personService;
    }
}