package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.PersonDto;
import org.example.service.impl.PersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonControllerImpl implements PersonController {
    private final PersonServiceImpl personService;
    private static final Logger logger = LoggerFactory.getLogger(PersonControllerImpl.class);

    @Override
    @PostMapping

    public PersonDto create(@RequestBody PersonDto dto) {
        logger.info("create");
        return personService.create(dto);
    }

    @Override
    @GetMapping("{id}")
    public PersonDto read(@PathVariable Long id) {
        logger.info("read");
        return personService.read(id);
    }

    @Override
    @PutMapping
    public void update(@RequestBody PersonDto dto) {
        logger.info("update");
        personService.update(dto);
    }

    @Override
    @DeleteMapping("/{person-id}")
    public void delete(@PathVariable("person-id") Long id) {
        logger.info("delete");
        personService.delete(id);
    }
}