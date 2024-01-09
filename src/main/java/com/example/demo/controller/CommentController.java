package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentController {
    CommentDto create(@Valid CommentDto commentDto);

    void delete(Long id);

    void deleteByAdvertisementId(Long id);

    Page<CommentDto> findAllByAdvertisementId(Long id, Pageable pageable);
}
