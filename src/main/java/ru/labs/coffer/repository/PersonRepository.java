package ru.labs.coffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.labs.coffer.entity.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Person> findByLogin(String login);

    Boolean existsByLogin(String login);

}
