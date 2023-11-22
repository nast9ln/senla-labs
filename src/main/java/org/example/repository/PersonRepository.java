package org.example.repository;

import org.example.GenericDao;
import org.example.entity.Person;

import java.util.List;
import java.util.Set;

public interface PersonRepository extends GenericDao<Person, Long> {
    List<Person> findByPersonName(String firstName, String lastName);
    Set<Person> findAllWithJPQL();
    Set<Person> findAllWithEntityGraph();

}
