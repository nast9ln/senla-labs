package org.example.service.impl;

import org.example.dto.PersonDto;
import org.example.entity.Person;
import org.example.repository.PersonRepository;
import org.example.service.PersonService;
import org.example.service.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class PersonServiceImpl implements PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private PersonMapper personDtoMapper;
    private PersonRepository personRepository;

    public PersonServiceImpl() {
    }

    @Autowired
    public PersonServiceImpl(PersonMapper personDtoMapper, PersonRepository personRepository) {
        this.personDtoMapper = personDtoMapper;
        this.personRepository = personRepository;
    }

    @Override
    public void create(PersonDto dto) {
        logger.info("create");
        Person person = personDtoMapper.toEntity(dto);
        personRepository.create(person);
    }

    @Override
    public PersonDto read(Long id) {
        logger.info("read");
        Person person = personRepository.read(id);
        return personDtoMapper.toDto(person);
    }

    @Override
    public void update(PersonDto dto) {
        logger.info("update");
        Person person = personDtoMapper.toEntity(dto);
        personRepository.update(person);
    }

    @Override
    public void delete(Long id) {
        logger.info("delete");
        personRepository.delete(id);
    }

}
