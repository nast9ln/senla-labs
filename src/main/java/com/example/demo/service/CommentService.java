package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    CommentDto create (CommentDto dto);
    CommentDto read (Long id);
    void update(CommentDto dto);
    void delete(Long id);
    void deleteByAdvertisementId (Long id);
    Page<CommentDto> findAllByAdvertisementId(Long id, Pageable pageable);
}
