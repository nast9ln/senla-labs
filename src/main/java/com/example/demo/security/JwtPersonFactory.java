package com.example.demo.security;

import com.example.demo.entity.Person;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class JwtPersonFactory {
    public static JwtPerson create(Person person) {
        return JwtPerson.builder()
                .login(person.getLogin())
                .password(person.getPassword())
                .isDeleted(person.isDeleted())
                .build();
    }

}
