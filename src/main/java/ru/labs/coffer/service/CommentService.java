package ru.labs.coffer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.labs.coffer.dto.CommentDto;

public interface CommentService {
    CommentDto create(CommentDto dto);

    CommentDto read(Long id);

    void update(CommentDto dto);

    void delete(Long id);

    void deleteByAdvertisementId(Long id);

    Page<CommentDto> findAllByAdvertisementId(Long id, Pageable pageable);
}
