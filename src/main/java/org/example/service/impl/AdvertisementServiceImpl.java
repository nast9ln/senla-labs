package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.dto.AdvertisementDto;
import org.example.entity.Advertisement;
import org.example.repository.AdvertisementRepository;
import org.example.service.AdvertisementService;
import org.example.service.mapper.AdvertisementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
    private final AdvertisementMapper advertisementDtoMapper;

    private final AdvertisementRepository advertisementRepository;


    @Override
    public AdvertisementDto create(AdvertisementDto dto) {
        Advertisement advertisement = advertisementDtoMapper.toEntity(dto);
        return advertisementDtoMapper.toDto(advertisementRepository.create(advertisement));
    }

    @Override
    public AdvertisementDto read(Long id) {
        Advertisement advertisement = advertisementRepository.get(id).orElseThrow();
        return advertisementDtoMapper.toDto(advertisement);
    }

    @Override
    public void update(AdvertisementDto dto) {
        Advertisement advertisement = advertisementDtoMapper.toEntity(dto);
        advertisementRepository.update(advertisement);
    }

    @Override
    public void delete(Long id) {
        advertisementRepository.delete(id);
    }
}
