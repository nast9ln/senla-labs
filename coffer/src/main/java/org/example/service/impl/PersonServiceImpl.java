package org.example.service.impl;

import org.example.dto.PersonDto;
import org.example.entity.Person;
import org.example.repository.AdvertisementRepository;
import org.example.repository.PersonRepository;
import org.example.repository.PersonRoleRepository;
import org.example.service.PersonService;
import org.example.service.mapper.PersonDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class PersonServiceImpl implements PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private PersonDtoMapper personDtoMapper;
    private PersonRepository personRepository;
    private AdvertisementRepository advertisementRepository;
    private PersonRoleRepository roleRepository;

    public PersonServiceImpl() {
    }

    @Autowired
    public PersonServiceImpl(PersonDtoMapper personDtoMapper, PersonRepository personRepository, AdvertisementRepository advertisementRepository, PersonRoleRepository roleRepository) {
        this.personDtoMapper = personDtoMapper;
        this.personRepository = personRepository;
        this.advertisementRepository = advertisementRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public PersonDto create(PersonDto dto) {
        logger.info("create");
        Person person = personDtoMapper.toEntity(dto);
        person = personRepository.create(person);
        try {
            roleRepository.createRelation(person.getId(), person.getRoles());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personDtoMapper.toDto(person);
    }

    @Override
    public PersonDto read(Long id) {
        logger.info("read");
        Person person = personRepository.read(id);
        return personDtoMapper.toDto(person);
    }

    @Override
    public PersonDto update(PersonDto dto) {
        logger.info("update");
        Person person = personDtoMapper.toEntity(dto);
        person = personRepository.update(person);
        return personDtoMapper.toDto(person);
    }

    @Override
    public void delete(Long id) {
        logger.info("delete");
        advertisementRepository.deleteByPersonId(id);
        personRepository.delete(id);
    }
}
