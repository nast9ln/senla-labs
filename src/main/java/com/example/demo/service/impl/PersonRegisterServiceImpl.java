package com.example.demo.service.impl;

import com.example.demo.entity.Person;
import com.example.demo.entity.Role;
import com.example.demo.enums.RoleEnum;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.PersonRegisterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
//    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Person register(Person person) {
        Role rolePerson = roleRepository.findByName(RoleEnum.ROLE_USER);
        Set<Role> personRoles = new HashSet<>();
        personRoles.add(rolePerson);

     //   person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRoles(personRoles);

        Person registerPerson = personRepository.save(person);

        log.info("user {} successfully registered", registerPerson);

        return registerPerson;
    }
}

