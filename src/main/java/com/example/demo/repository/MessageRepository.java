package com.example.demo.repository;

import com.example.demo.entity.Message;
import com.example.demo.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.sender.id = :personId AND m.advertisement.id = :advertisementId")
    Page<Message> getDialog(@Param("personId") Long personId, @Param("advertisementId") Long advertisement, Pageable pageable);
    @Query("SELECT DISTINCT m.sender FROM Message m WHERE m.advertisement.person.id = :creatorId" )
    Page<Person> findPersonsWithDialogByCreator(@Param("creatorId") Long creatorId, Pageable pageable);
    @Query("SELECT m FROM Message m WHERE (m.advertisement.id =:advertisementId) " +
            "AND (m.sender.id=:personId OR m.advertisement.person.id=:creatorId)")
    Page<Message> findDialogByCreator(@Param("advertisementId") Long advertisementId, @Param("creatorId") Long creatorId,
                                      @Param("personId") Long personId, Pageable pageable);

}
