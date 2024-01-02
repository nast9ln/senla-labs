package com.example.demo.service.mapper;

import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface PersonMapper {
    Person toEntity(PersonDto dto);

    PersonDto toDto(Person entity);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Person exPerson, Person newPerson);
}
