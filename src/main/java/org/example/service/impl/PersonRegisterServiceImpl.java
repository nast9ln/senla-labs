package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Person;
import org.example.entity.Role;
import org.example.enums.RoleEnum;
import org.example.repository.PersonRepository;
import org.example.repository.RoleRepository;
import org.example.service.PersonRegisterService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PersonRegisterServiceImpl implements PersonRegisterService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Person register(Person person) {
        Role rolePerson = roleRepository.findByName(RoleEnum.ROLE_USER);
        Set<Role> personRoles = new HashSet<>();
        personRoles.add(rolePerson);

        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRoles(personRoles);

        Person registerPerson = personRepository.save(person);

        log.info("user {} successfully registered", registerPerson);

        return registerPerson;
    }
}
