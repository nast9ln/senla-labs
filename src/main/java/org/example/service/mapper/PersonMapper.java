package org.example.service.mapper;

import org.example.dto.PersonDto;
import org.example.dto.RoleDto;
import org.example.entity.Person;
import org.example.entity.Role;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

   @Transactional
    public Person toEntity(PersonDto dto) {
        return Person.builder()
                .id(dto.getId())
                .gender(dto.getGender())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthday(Instant.ofEpochSecond(dto.getBirthday()))
                .city(dto.getCity())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .isDeleted(dto.isDeleted())
                .roles(new HashSet<>(dto.getRoles().stream().map(this::toRole).collect(Collectors.toList())))
                .build();
    }

    private RoleDto toRoleDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    private Role toRole(RoleDto role) {
        return Role.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    @Transactional
    public PersonDto toDto(Person entity) {
        return PersonDto.builder()
                .id(entity.getId())
                .gender(entity.getGender())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthday(entity.getBirthday().getEpochSecond())
                .city(entity.getCity())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .isDeleted(entity.isDeleted())
                .roles(entity.getRoles().stream().map(this::toRoleDto).collect(Collectors.toList()))
                .build();
    }
}
