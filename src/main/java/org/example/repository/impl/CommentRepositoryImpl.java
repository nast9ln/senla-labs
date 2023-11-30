package org.example.repository.impl;

import org.example.entity.Comment;
import org.example.repository.CommentRepository;

public class CommentRepositoryImpl extends AbstractDaoImpl<Comment, Long> implements CommentRepository {
    public CommentRepositoryImpl(Class<Comment> type) {
        super(type);
    }
}
