package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentController {
    CommentDto create (CommentDto commentDto);
    CommentDto read (Long id);
    void update (CommentDto dto);
    void delete(Long id);
    void deleteByAdvertisementId(Long id);
    Page<CommentDto> findAllByAdvertisementId(Long id, Pageable pageable);
}
