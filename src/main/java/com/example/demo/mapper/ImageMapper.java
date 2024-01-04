package com.example.demo.mapper;

import com.example.demo.dto.ImageDto;
import com.example.demo.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ImageMapper {
    Image toEntity(ImageDto dto);

    ImageDto toDto(Image entity);

    void update(Image exImage, @MappingTarget Image newImage);

}
