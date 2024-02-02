package ru.labs.coffer.mapper;

import org.mapstruct.*;
import org.mapstruct.control.MappingControl;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Mapping(target = "password", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Person exPerson, Person newPerson);
}
