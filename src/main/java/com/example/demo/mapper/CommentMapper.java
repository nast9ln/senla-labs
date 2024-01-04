package com.example.demo.mapper;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(uses = {PersonMapper.class, AdvertisementMapper.class})
public interface CommentMapper {
    Comment toEntity(CommentDto dto);

    CommentDto toDto(Comment comment);

    void update(Comment exComment, @MappingTarget Comment newComment);
}
