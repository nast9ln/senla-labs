package com.example.demo.mapper;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.entity.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PersonMapper.class, CategoryMapper.class, TopParamMapper.class, ImageMapper.class})
public interface AdvertisementMapper {
    AdvertisementMapper INSTANCE = Mappers.getMapper(AdvertisementMapper.class);

    Advertisement toEntity(AdvertisementDto dto);

    AdvertisementDto toDto(Advertisement advertisement);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "topParam", ignore = true)
    void update(Advertisement exAd, @MappingTarget Advertisement newAd);

}