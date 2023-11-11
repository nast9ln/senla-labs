package org.example.repository;

import org.example.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepositoryJpa extends JpaRepository<Person, Long> {

//    @Query("select p from person p " +
//            "join " +
//            "where r.name = :name")
    //List<Person> findPersonWithRole(String name);

}
