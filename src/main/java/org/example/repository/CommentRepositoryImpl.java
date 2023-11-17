package org.example.repository;

import org.example.AbstractDaoImpl;
import org.example.entity.Comment;

public class CommentRepositoryImpl extends AbstractDaoImpl<Comment, Long> implements CommentRepository {
    public CommentRepositoryImpl(Class<Comment> type) {
        super(type);
    }
}
