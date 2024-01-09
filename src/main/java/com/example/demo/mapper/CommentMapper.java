package com.example.demo.mapper;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import org.mapstruct.*;

@Mapper(uses = {PersonMapper.class, AdvertisementMapper.class})
public interface CommentMapper {
    @InheritInverseConfiguration
    Comment toEntity(CommentDto dto);
    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "advertisementId", source = "advertisement.id")

    CommentDto toDto(Comment comment);

    void update(Comment exComment, @MappingTarget Comment newComment);
}
