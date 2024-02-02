package ru.labs.coffer.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.entity.Role;
import ru.labs.coffer.enums.RoleEnum;
import ru.labs.coffer.repository.PersonRepository;
import ru.labs.coffer.repository.RoleRepository;
import ru.labs.coffer.service.PersonRegisterService;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PersonRegisterServiceImpl implements PersonRegisterService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    @Override
    public Person register(Person person) {
        Role rolePerson = roleRepository.findByName(RoleEnum.ROLE_USER);
        Set<Role> personRoles = new HashSet<>();
        personRoles.add(rolePerson);

        person.setRoles(personRoles);

        Person registerPerson = personRepository.save(person);

        log.info("user {} successfully registered", registerPerson);

        return registerPerson;
    }
}

