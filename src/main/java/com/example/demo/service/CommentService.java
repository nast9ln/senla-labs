package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    CommentDto create(CommentDto dto);

    CommentDto read(Long id);

    void update(CommentDto dto);

    void delete(Long id);

    void deleteByAdvertisementId(Long id);

    Page<CommentDto> findAllByAdvertisementId(Long id, Pageable pageable);
}
