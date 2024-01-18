package com.example.demo.mapper;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = {PersonMapper.class, AdvertisementMapper.class})
public interface CommentMapper {
    @InheritInverseConfiguration
    Comment toEntity(CommentDto dto);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "advertisementId", source = "advertisement.id")
    CommentDto toDto(Comment comment);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Comment exComment, Comment newComment);
}
