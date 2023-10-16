package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.PersonDto;
import org.example.entity.Person;
import org.example.repository.PersonRepository;
import org.example.service.PersonService;
import org.example.service.mapper.PersonDtoMapper;

@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonDtoMapper personDtoMapper;

    @Override
    public String execute(PersonDto dto) {
        log.info("execute");
        Person person = personDtoMapper.toEntity(dto);
        return personRepository.execute(person);

    }

    @Override
    public void create(PersonDto dto) {
        log.info("create");
        Person person = personDtoMapper.toEntity(dto);
        personRepository.create(person);
    }

    @Override
    public PersonDto read(Long id) {
        log.info("read");
        Person person = personRepository.read(id);
        return personDtoMapper.toDto(person);
    }

    @Override
    public void update(PersonDto dto) {
        log.info("update");
        Person person = personDtoMapper.toEntity(dto);
        personRepository.update(person);
    }

    @Override
    public void delete(Long id) {
        log.info("delete");
        personRepository.delete(id);
    }
}
