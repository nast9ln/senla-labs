package org.example.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.dto.AdvertisementDto;
import org.example.entity.Advertisement;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdvertisementMapper {

    private final PersonMapper personMapper;
    public Advertisement toEntity(AdvertisementDto dto) {
        return Advertisement.builder()
                .id(dto.getId())
                .person(personMapper.toEntity(dto.getPerson()))
                .categoryId(dto.getCategory())
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
                .person(personMapper.toDto(entity.getPerson()))
                .category(entity.getCategoryId())
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
