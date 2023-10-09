package org.example.main.service.impl;

import org.example.di.annotation.Autowired;
import org.example.di.annotation.Component;
import org.example.main.dto.PersonDto;
import org.example.main.entity.Person;
import org.example.main.repository.PersonRepository;
import org.example.main.repository.PersonRepositoryImpl;
import org.example.main.service.mapper.PersonDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class PersonService implements org.example.main.service.PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private static PersonDtoMapper personDtoMapper;
    @Autowired
    private static PersonRepository personRepository;

    public PersonService() {
    }

    public PersonService(PersonDtoMapper personDtoMapper, PersonRepository personRepository) {
        PersonService.personDtoMapper = personDtoMapper;
        PersonService.personRepository = personRepository;
    }


    @Override
    public String execute(PersonDto dto) {
        logger.info("execute");
        Person person = personDtoMapper.toEntity(dto);
        return personRepository.execute(person);

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

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public void setPersonRepository(PersonRepositoryImpl personRepository) {
        PersonService.personRepository = personRepository;
    }


    public PersonDtoMapper getPersonDtoMapper() {
        return personDtoMapper;
    }

    public void setPersonDtoMapper(PersonDtoMapper personDtoMapper) {
        PersonService.personDtoMapper = personDtoMapper;
    }
}
