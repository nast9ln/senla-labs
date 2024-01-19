package ru.labs.coffer.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import ru.labs.coffer.dto.CommentDto;

public interface CommentController {
    ResponseEntity<CommentDto> create(@Valid CommentDto commentDto);

    ResponseEntity<Void> delete(Long id);

    ResponseEntity<Void>  deleteByAdvertisementId(Long id);

    ResponseEntity<Page<CommentDto>> findAllByAdvertisementId(Long id, Pageable pageable);
}
