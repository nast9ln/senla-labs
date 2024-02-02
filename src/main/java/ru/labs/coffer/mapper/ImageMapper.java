package ru.labs.coffer.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.labs.coffer.dto.ImageDto;
import ru.labs.coffer.entity.Image;

@Mapper
public interface ImageMapper {
    @InheritInverseConfiguration
    Image toEntity(ImageDto dto);

    @Mapping(target = "advertisementId", source = "advertisement.id")
    ImageDto toDto(Image entity);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Image exImage, Image newImage);

}
