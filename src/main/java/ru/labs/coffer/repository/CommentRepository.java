package ru.labs.coffer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.labs.coffer.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByAdvertisementId(Long id, Pageable pageable);

    List<Comment> findAllByAdvertisementId(Long id);

}
