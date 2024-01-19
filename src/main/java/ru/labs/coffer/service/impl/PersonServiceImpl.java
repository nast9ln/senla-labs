package ru.labs.coffer.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.dto.RoleDto;
import ru.labs.coffer.dto.security.JwtPerson;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.entity.Role;
import ru.labs.coffer.exception.EntityNotFoundException;
import ru.labs.coffer.mapper.AdvertisementMapper;
import ru.labs.coffer.mapper.PersonMapper;
import ru.labs.coffer.mapper.RoleMapper;
import ru.labs.coffer.repository.AdvertisementRepository;
import ru.labs.coffer.repository.PersonRepository;
import ru.labs.coffer.service.AdvertisementService;
import ru.labs.coffer.service.PersonService;
import ru.labs.coffer.service.security.JwtAuthorizationService;

import java.util.HashSet;
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
    private final RoleMapper roleMapper;

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
    public void delete() {
        JwtPerson jwtPerson = jwtAuthorizationService.extractJwtPerson();
        Person person = personRepository.findById(jwtPerson.getId()).orElseThrow(() -> new EntityNotFoundException("User not found with id {0}", jwtPerson.getId()));
        advertisementService.deleteByPersonId(jwtPerson.getId());
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

    @Override
    public void updatePersonRole(Long id, Set<RoleDto> rolesDto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id {0}", id));
        Set<Role> roles = new HashSet<>();
        for (RoleDto role : rolesDto) {
            roleMapper.toEntity(role);
            roles.add(roleMapper.toEntity(role));
        }
        person.setRoles(roles);
        personRepository.save(person);
    }
}
