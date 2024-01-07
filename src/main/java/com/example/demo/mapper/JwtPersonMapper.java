package com.example.demo.mapper;

import com.example.demo.dto.security.JwtPerson;
import com.example.demo.entity.Person;
import org.mapstruct.Mapper;

@Mapper
public interface JwtPersonMapper {

    JwtPerson toJwtPerson(Person person);

    Person toPerson(JwtPerson jwtPerson);

}
