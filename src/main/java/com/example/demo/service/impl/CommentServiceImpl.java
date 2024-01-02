package com.example.demo.service.impl;

import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public List<Comment> findAllByAdvertisement(Advertisement advertisement) {
        return commentRepository.findAllByAdvertisement(advertisement);
    }
}
