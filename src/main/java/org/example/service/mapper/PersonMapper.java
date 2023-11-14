package org.example.service.mapper;

import org.example.dto.PersonDto;
import org.example.dto.RoleDto;
import org.example.entity.Person;
import org.example.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toEntity(PersonDto dto) {
        return Person.builder()
                .id(dto.getId())
                .gender(dto.getGender())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthday(dto.getBirthday())
                .city(dto.getCity())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .isDeleted(dto.isDeleted())
                .roles(dto.getRoles().stream().map(this::toRole).toList())
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
                .roles(entity.getRoles().stream().map(this::toRoleDto).toList())
                .build();
    }
}
