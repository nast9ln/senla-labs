package org.example.service.mapper;

import org.example.dto.PersonDto;
import org.example.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoMapper {
    public PersonDtoMapper() {
    }

    public Person toEntity(PersonDto dto) {
        return Person.builder()
                .id(dto.getId())
                .gender(dto.getGender())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .city(dto.getCity())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
    }

    public PersonDto toDto(Person entity) {
        return PersonDto.builder()
                .id(entity.getId())
                .gender(entity.getGender())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .city(entity.getCity())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .build();
    }
}
