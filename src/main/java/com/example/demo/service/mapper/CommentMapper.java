package com.example.demo.service.mapper;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {PersonMapper.class, AdvertisementMapper.class})
public interface CommentMapper {
    Comment toEntity(CommentDto dto);
    CommentDto toDto(Comment comment);
}
