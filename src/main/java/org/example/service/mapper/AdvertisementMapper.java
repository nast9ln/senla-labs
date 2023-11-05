package org.example.service.mapper;

import org.example.dto.AdvertisementDto;
import org.example.entity.Advertisement;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementMapper {
    public Advertisement toEntity(AdvertisementDto dto) {
        return Advertisement.builder()
                .id(dto.getId())
                .personId(dto.getPersonId())
                .categoryId(dto.getCategoryId())
                .mainImageId(dto.getMainPictureId())
                .topParamId(dto.getTopParamId())
                .createdDate(dto.getCreatedData())
                .cost(dto.getCost())
                .city(dto.getCity())
                .header(dto.getHeader())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
    }

    public AdvertisementDto toDto(Advertisement entity) {
        return AdvertisementDto.builder()
                .id(entity.getId())
                .personId(entity.getPersonId())
                .categoryId(entity.getCategoryId())
                .mainPictureId(entity.getMainImageId())
                .topParamId(entity.getTopParamId())
                .createdData(entity.getCreatedDate())
                .cost(entity.getCost())
                .city(entity.getCity())
                .header(entity.getHeader())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .build();
    }
}
