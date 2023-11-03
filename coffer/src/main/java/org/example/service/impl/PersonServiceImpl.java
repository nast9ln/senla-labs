package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.config.MyTransaction;
import org.example.dto.AdvertisementDto;
import org.example.dto.PersonDto;
import org.example.entity.Advertisement;
import org.example.entity.Person;
import org.example.entity.Role;
import org.example.enums.RoleEnum;
import org.example.repository.AdvertisementRepository;
import org.example.repository.PersonRepository;
import org.example.repository.PersonRoleRepository;
import org.example.service.PersonService;
import org.example.service.mapper.AdvertisementMapper;
import org.example.service.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonMapper personDtoMapper;
    private final PersonRepository personRepository;
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementDtoMapper;
    private final PersonRoleRepository roleRepository;

    @Override
    @MyTransaction
    public PersonDto create(PersonDto dto) {
        logger.info("create");
        Person person = personDtoMapper.toEntity(dto);
        person = personRepository.create(person);
        System.out.println("mm");
        try {
            if (person.getRoles() == null)
                person.setRoles(Arrays.asList(new Role(2L, RoleEnum.USER)));
            roleRepository.createRelation(person.getId(), person.getRoles());
        } catch (SQLException e) {
            System.out.println("vvv");
            throw new RuntimeException(e);
        }
        return personDtoMapper.toDto(person);
    }

    @Override
    @MyTransaction
    public PersonDto read(Long id) {
        logger.info("read");
        Person person = personRepository.read(id);
        List<Advertisement> advertisements;
        List<AdvertisementDto> advertisementDtos = new ArrayList<>();

        advertisements=advertisementRepository.readByPersonId(id);

        for (Advertisement advertisement : advertisements) {
            advertisementDtos.add(advertisementDtoMapper.toDto(advertisement));
        }
        PersonDto personDto = personDtoMapper.toDto(person);
        personDto.setAdvertisementDto(advertisementDtos);
        return personDto;
    }

    @Override
    @MyTransaction
    public PersonDto update(PersonDto dto) {
        logger.info("update");
        Person person = personDtoMapper.toEntity(dto);
        person = personRepository.update(person);
        return personDtoMapper.toDto(person);
    }

    @Override
    @MyTransaction
    public void delete(Long id) {
        logger.info("delete");
        advertisementRepository.deleteByPersonId(id);
        personRepository.delete(id);
    }
}
