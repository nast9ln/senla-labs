package org.example.service.mapper;

import org.example.dto.PersonDto;
import org.example.dto.RoleDto;
import org.example.entity.Person;
import org.example.entity.Role;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public Person toEntity(PersonDto dto) {
        return Person.builder()
                .id(dto.getId())
                .gender(dto.getGender())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthday((dto.getBirthday()))
                .city(dto.getCity())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .isDeleted(dto.isDeleted())
                .roles(new HashSet<>(dto.getRoles().stream().filter(Objects::nonNull).map(this::toRole).collect(Collectors.toList())))
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

    public PersonDto toDto(Person entity) {
        return PersonDto.builder()
                .id(entity.getId())
                .gender(entity.getGender())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthday(entity.getBirthday())
                .city(entity.getCity())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .isDeleted(entity.isDeleted())
                .roles(entity.getRoles().stream().filter(Objects::nonNull).map(this::toRoleDto).collect(Collectors.toSet()))
                .build();
    }

    public void update(Person exPerson, Person newPerson) {
        exPerson.setGender(newPerson.getGender());
        exPerson.setFirstName(newPerson.getFirstName());
        exPerson.setLastName(newPerson.getLastName());
        exPerson.setBirthday(newPerson.getBirthday());
        exPerson.setCity(newPerson.getCity());
        exPerson.setPhone(newPerson.getPhone());
        exPerson.setEmail(newPerson.getEmail());
        exPerson.setPassword(newPerson.getPassword());
        exPerson.setDeleted(newPerson.isDeleted());
    }
}
