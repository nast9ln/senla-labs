package ru.labs.coffer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.entity.Person;

@Mapper
public interface PersonMapper {
    Person toEntity(PersonDto dto);

    PersonDto toDto(Person entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "advertisements", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    void update(@MappingTarget Person exPerson, Person newPerson);
}
