package ru.labs.coffer.mapper;

import org.mapstruct.Mapper;
import ru.labs.coffer.dto.security.JwtPerson;
import ru.labs.coffer.entity.Person;

@Mapper
public interface JwtPersonMapper {

    JwtPerson toJwtPerson(Person person);

    Person toPerson(JwtPerson jwtPerson);

}
