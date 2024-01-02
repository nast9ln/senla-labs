package com.example.demo.service;

import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAllByAdvertisement(Advertisement advertisement);
}
