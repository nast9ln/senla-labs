package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.AdvertisementDto;
import org.example.dto.PersonDto;
import org.example.entity.Advertisement;
import org.example.entity.Person;
import org.example.exception.EntityNotFoundException;
import org.example.repository.AdvertisementRepository;
import org.example.repository.PersonRepository;
import org.example.service.AdvertisementService;
import org.example.service.PersonService;
import org.example.service.mapper.AdvertisementMapper;
import org.example.service.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonMapper personDtoMapper;
    private final PersonRepository personRepository;
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementService advertisementService;
    private final AdvertisementMapper advertisementDtoMapper;

    @Override
    public PersonDto read(Long id) {
        logger.info("read");
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id {0}:", id));
        List<Advertisement> advertisements = advertisementRepository.findByPersonId(id);
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
        Person exPerson = personRepository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("Ad not found with id: {0}", dto.getId()));
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
        return personRepository.findByLogin(login);
    }
}
