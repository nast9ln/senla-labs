package com.example.demo.service.mapper;

import com.example.demo.dto.ImageDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {AdvertisementMapper.class})
public interface ImageMapper {
    Image toEntity(ImageDto dto);

    ImageDto toDto (Image entity);

    void update (Image exImage, @MappingTarget Image newImage);

}
