package org.example.service.mapper;

import org.example.dto.AdvertisementDto;
import org.example.entity.Advertisement;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementDtoMapper {
    public Advertisement toEntity(AdvertisementDto dto) {
        return Advertisement.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .categoryId(dto.getCategoryId())
                .mainPictureId(dto.getMainPictureId())
                .topParamId(dto.getTopParamId())
                .createdDate(dto.getCreatedData())
                .cost(dto.getCost())
                .city(dto.getCity())
                .header(dto.getHeader())
                .description(dto.getDescription())
                .type(dto.getType())
                .status(dto.getStatus())
                .build();
    }

    public AdvertisementDto toDto(Advertisement entity) {
        return AdvertisementDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .categoryId(entity.getCategoryId())
                .mainPictureId(entity.getMainPictureId())
                .topParamId(entity.getTopParamId())
                .createdData(entity.getCreatedDate())
                .cost(entity.getCost())
                .city(entity.getCity())
                .header(entity.getHeader())
                .description(entity.getDescription())
                .type(entity.getType())
                .status(entity.getStatus())
                .build();
    }
}
