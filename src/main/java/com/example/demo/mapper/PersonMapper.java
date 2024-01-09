package com.example.demo.mapper;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PersonMapper {
    Person toEntity(PersonDto dto);
    PersonDto toDto(Person entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "advertisements", ignore = true)
    void update(@MappingTarget Person exPerson, Person newPerson);
}
