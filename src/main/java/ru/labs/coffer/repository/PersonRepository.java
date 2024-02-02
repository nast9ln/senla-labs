package ru.labs.coffer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByLogin(String login);
}
