package org.example.repository;

import org.example.GenericDao;
import org.example.entity.Person;

import java.util.List;

public interface PersonRepository extends GenericDao<Person, Long> {

    List<Person> findAllByPersonName(String firstName, String lastName);

}
