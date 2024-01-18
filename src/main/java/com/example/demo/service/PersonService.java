package com.example.demo.service;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PersonService {
    PersonDto read(Long id);

    void update(PersonDto personDto);

    void delete(Long id);

    void delete();

    Person findByLogin(String login);

    Page<AdvertisementDto> findAdvertisementByPersonId(Pageable pageable);

    void updatePersonRole(Long id, Set<RoleDto> roles);
}