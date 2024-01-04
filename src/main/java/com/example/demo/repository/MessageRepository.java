package com.example.demo.repository;

import com.example.demo.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.sender.id = :personId AND m.advertisement.id = :advertisementId")
    Page<Message> getDialog(@Param("personId") Long personId, @Param("advertisementId") Long advertisement, Pageable pageable);
}
