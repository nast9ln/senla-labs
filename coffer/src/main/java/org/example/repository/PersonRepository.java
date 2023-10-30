package org.example.repository;

import org.example.entity.Person;
import org.example.service.CRUD;

import java.sql.Connection;

public interface PersonRepository extends CRUD<Person> {
}
