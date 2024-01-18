package com.example.demo.mapper;

import com.example.demo.dto.ImageDto;
import com.example.demo.entity.Image;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ImageMapper {
    @InheritInverseConfiguration
    Image toEntity(ImageDto dto);

    @Mapping(target = "advertisementId", source = "advertisement.id")
    ImageDto toDto(Image entity);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Image exImage, Image newImage);

}
