package com.example.demo.service.mapper;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Person;
import lombok.RequiredArgsConstructor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {PersonMapper.class, CategoryMapper.class, TopParamMapper.class})
public interface AdvertisementMapper {
    Advertisement toEntity(AdvertisementDto dto);

    AdvertisementDto toDto (Advertisement advertisement);

    void update (Advertisement exAd, @MappingTarget Advertisement newAd);
}