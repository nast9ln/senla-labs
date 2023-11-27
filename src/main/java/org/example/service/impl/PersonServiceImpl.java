package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.AdvertisementDto;
import org.example.dto.PersonDto;
import org.example.entity.Advertisement;
import org.example.entity.Person;
import org.example.entity.Role;
import org.example.enums.RoleEnum;
import org.example.exception.EntityNotFoundException;
import org.example.repository.AdvertisementRepository;
import org.example.repository.PersonRepository;
import org.example.repository.RoleRepository;
import org.example.service.PersonService;
import org.example.service.mapper.AdvertisementMapper;
import org.example.service.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@Component
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonMapper personDtoMapper;
    private final PersonRepository personRepository;
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementDtoMapper;
    private final RoleRepository roleRepository;

    @Override
    public PersonDto create(PersonDto dto) {
        logger.info("create");
        Person person = personDtoMapper.toEntity(dto);
        person = personRepository.create(person);
        Set<Role> roles = new HashSet<>();
        if (person.getRoles() == null)
            person.setRoles(new HashSet<>(Arrays.asList(new Role(2L, RoleEnum.USER))));
        person.getRoles().forEach(r -> roleRepository.findByName(r.getName()).add(r));
        person.setRoles(roles);
        return personDtoMapper.toDto(person);
    }

    @Override
    public PersonDto read(Long id) {
        logger.info("read");
        Person person = personRepository.get(id).orElseThrow(()->new EntityNotFoundException("Не найден пользователь с id {0}", id));
        List<Advertisement> advertisements = advertisementRepository.readByPersonId(id);
        List<AdvertisementDto> advertisementDtos = new ArrayList<>();

        for (Advertisement advertisement : advertisements) {
            advertisementDtos.add(advertisementDtoMapper.toDto(advertisement));
        }

        PersonDto personDto = personDtoMapper.toDto(person);
        personDto.setAdvertisementDto(advertisementDtos);
        return personDto;
    }

    @Override
    public void update(PersonDto dto) {
        logger.info("update");
        Person newPerson = personDtoMapper.toEntity(dto);
        Person exPerson = personRepository.get(dto.getId()).orElseThrow(()-> new EntityNotFoundException("Не найдено объявление с id {0}", dto.getId()));
        personDtoMapper.update(exPerson, newPerson);
        personRepository.update(exPerson);
    }

    @Override
    public void delete(Long id) {
        logger.info("delete");
        Person person = personRepository.get(id).orElseThrow(()->new EntityNotFoundException("Не найден пользователь с id {0}", id));
        advertisementRepository.deleteByPersonId(id);
        personRepository.delete(id);
    }

    public Set<Person> findAllWithJPQL() {
        return personRepository.findAllWithJPQL();
    }

    public Set<Person> findAllWithEntityGraph() {
        return personRepository.findAllWithEntityGraph();
    }
}
