package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.dto.PersonDto;
import org.example.service.CRUD;
import org.example.service.impl.PersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PersonController implements CRUD<PersonDto> {

    private final ObjectMapper objectMapper;
    private final PersonServiceImpl personService;
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public String execute(PersonDto dto) {
        logger.info("execute");
        return personService.execute(dto);
    }

    public String parseToJson(PersonDto dto) throws JsonProcessingException {
        logger.info("parseToJson");
        return objectMapper.writeValueAsString(dto);
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

}