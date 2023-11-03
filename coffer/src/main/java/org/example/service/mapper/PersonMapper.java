package org.example.service.mapper;

import org.example.dto.PersonDto;
import org.example.entity.Person;
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
                .build();
    }
}
