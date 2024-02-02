package ru.labs.coffer.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.labs.coffer.dto.CommentDto;
import ru.labs.coffer.entity.Comment;

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
