package org.example.main.repository;

import org.example.main.entity.Person;
import org.example.main.service.CRUD;

public interface PersonRepository extends CRUD<Person> {
    String execute(Person person);
}
