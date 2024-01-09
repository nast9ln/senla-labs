package com.example.demo.service.impl;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.dto.security.JwtPerson;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Person;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.service.AdvertisementService;
import com.example.demo.service.PersonService;
import com.example.demo.mapper.AdvertisementMapper;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.service.security.JwtAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonMapper personDtoMapper;
    private final PersonRepository personRepository;
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementService advertisementService;
    private final AdvertisementMapper advertisementDtoMapper;
    private final JwtAuthorizationService jwtAuthorizationService;

    @Override
    public PersonDto read(Long id) {
        logger.info("read");
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id {0}:", id));
        return personDtoMapper.toDto(person);
    }

    @Override
    public void update(PersonDto dto) {
        logger.info("update");
        Person newPerson = personDtoMapper.toEntity(dto);
        JwtPerson jwtPerson = jwtAuthorizationService.extractJwtPerson();
        Person exPerson = personRepository.findById(jwtPerson.getId()).orElseThrow(() -> new EntityNotFoundException("Person not found with id: {0}", dto.getId()));
        personDtoMapper.update(exPerson, newPerson);
        personRepository.save(exPerson);
    }

    @Override
    public void delete(Long id) {
        logger.info("delete");
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id {0}", id));
        advertisementService.deleteByPersonId(person.getId());
        person.setDeleted(true);
        personRepository.save(person);
    }

    @Override
    public Person findByLogin(String login) {
        return personRepository.findByLogin(login).orElseThrow(() -> new EntityNotFoundException("Person not found with login {0}", login));
    }

    @Override
    public Page<AdvertisementDto> findAdvertisementByPersonId(Pageable pageable) {
        JwtPerson jwtPerson = jwtAuthorizationService.extractJwtPerson();

        return advertisementRepository.findByPersonId(jwtPerson.getId(), pageable).map(advertisementDtoMapper::toDto);
    }
}
