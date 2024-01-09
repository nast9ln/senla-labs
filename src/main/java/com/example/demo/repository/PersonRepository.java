package com.example.demo.repository;

import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Person> findByLogin(String login);
//    @Query("SELECT adv FROM Advertisement adv WHERE adv.person.id = :personId")
//    Set<Advertisement> findAdvertisementByPersonId(@Param("personId") Long personId);
}
