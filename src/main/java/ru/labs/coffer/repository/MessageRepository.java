package ru.labs.coffer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.Message;
import ru.labs.coffer.entity.Person;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findAll(Specification<Message> specification, Pageable pageable);

    @Query("SELECT DISTINCT m.sender FROM Message m WHERE m.advertisement.person.id = :creatorId")
    Page<Person> findPersonsWithDialogByCreator(@Param("creatorId") Long creatorId, Pageable pageable);

    @Query("SELECT m FROM Message m " +
            "WHERE (m.sender.id = :creatorId AND m.recipient.id = :personId AND m.advertisement.id = :advertisementId) " +
            "   OR (m.sender.id = :personId AND m.advertisement.id = :advertisementId)")
    Page<Message> findDialogByCreator(@Param("advertisementId") Long advertisementId, @Param("creatorId") Long creatorId,
                                      @Param("personId") Long personId, Pageable pageable);

    @Query("SELECT COUNT(m) > 0 FROM Message m WHERE m.sender.id = :personId AND m.advertisement.person.id =:creatorId")
    Boolean existsDialog(@Param("personId") Long personId, @Param("creatorId") Long creatorId);

}
