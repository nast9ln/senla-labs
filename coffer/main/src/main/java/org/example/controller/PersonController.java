package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.PersonDto;
import org.example.service.CRUD;
import org.example.service.PersonService;

//работает с сервисами . работает с сервисами. не с сущностями, а с дто. дто чтоб не менять сущность
@Slf4j
@RequiredArgsConstructor
public class PersonController implements CRUD<PersonDto> {
    private final PersonService personService;


    // сервис с дто
    //контроллер с дто

    public String execute(PersonDto dto) {
        log.info("execute");
        return personService.execute(dto);
    }

    @Override
    public void create(PersonDto dto) {
        log.info("create");
        personService.create(dto);
    }

    @Override
    public PersonDto read(Long id) {
        log.info("read");
        return personService.read(id);
    }

    @Override
    public void update(PersonDto dto) {
        log.info("update");
        personService.update(dto);
    }

    @Override
    public void delete(Long id) {
        log.info("delete");
        personService.delete(id);
    }
}