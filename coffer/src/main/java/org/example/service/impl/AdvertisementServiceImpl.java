package org.example.service.impl;

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

    @Autowired
    private final AdvertisementRepository advertisementRepository;

    @Override
    public void create(AdvertisementDto dto) {
        Advertisement advertisement = advertisementDtoMapper.toEntity(dto);
        advertisementRepository.create(advertisement);
    }

    @Override
    public AdvertisementDto read(Long id) {
        Advertisement advertisement = advertisementRepository.read(id);
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
