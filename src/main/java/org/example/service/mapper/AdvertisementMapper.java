package org.example.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.dto.AdvertisementDto;
import org.example.entity.Advertisement;
import org.example.entity.Person;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdvertisementMapper {

    private final PersonMapper personMapper;
    public Advertisement toEntity(AdvertisementDto dto) {
        Person person = new Person();
        if (Objects.nonNull(dto.getPerson())) {
            person.setId(dto.getPerson().getId());
        }
        return Advertisement.builder()
                .id(dto.getId())
                .person(person)
                .categoryId(dto.getCategory())
                .mainImageId(dto.getMainPictureId())
                .topParamId(dto.getTopParamId())
                .createdDate(Instant.ofEpochSecond(Optional.ofNullable(dto.getCreatedData()).orElse(0L)))
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
                .createdData(entity.getCreatedDate().getEpochSecond())
                .cost(entity.getCost())
                .city(entity.getCity())
                .header(entity.getHeader())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .build();
    }
}
