package ru.labs.coffer.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.entity.Advertisement;

@Mapper(componentModel = "spring", uses = {PersonMapper.class, CategoryMapper.class, TopParamMapper.class, ImageMapper.class})
public interface AdvertisementMapper {
    AdvertisementMapper INSTANCE = Mappers.getMapper(AdvertisementMapper.class);

    Advertisement toEntity(AdvertisementDto dto);

    AdvertisementDto toDto(Advertisement advertisement);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "topParam", ignore = true)
    @Mapping(target = "status", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Advertisement exAd, Advertisement newAd);

}