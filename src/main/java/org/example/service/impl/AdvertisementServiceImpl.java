package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.AdvertisementDto;
import org.example.entity.Advertisement;
import org.example.entity.Person;
import org.example.exception.EntityNotFoundException;
import org.example.exception.RelativeNotFoundException;
import org.example.repository.AdvertisementRepository;
import org.example.repository.PersonRepository;
import org.example.service.AdvertisementService;
import org.example.service.mapper.AdvertisementMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@Transactional
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
    private final AdvertisementMapper advertisementDtoMapper;
    private final AdvertisementRepository advertisementRepository;
    private final PersonRepository personRepository;

    @Override
    public AdvertisementDto create(AdvertisementDto dto) {
        dto.setId(null);
        Advertisement advertisement = advertisementDtoMapper.toEntity(dto);
        Long personId = advertisement.getPerson().getId();
        if (Objects.isNull(personId)) {
            throw new RelativeNotFoundException("Person id must be not null");
        }
        Person person = personRepository.get(personId)
                .orElseThrow(() -> new EntityNotFoundException("The user for the ad was not found with the id: {0}", personId));
        advertisement.setPerson(person);
        return advertisementDtoMapper.toDto(advertisementRepository.create(advertisement));
    }

    @Override
    public AdvertisementDto read(Long id) {
        Advertisement advertisement = advertisementRepository.get(id).orElseThrow(() -> new EntityNotFoundException("Ad not found with id: {0}", id));
        return advertisementDtoMapper.toDto(advertisement);
    }

    @Override
    public void update(AdvertisementDto dto) {
        Advertisement newAd = advertisementDtoMapper.toEntity(dto);
        Advertisement exAd = advertisementRepository.get(dto.getId()).orElseThrow(() -> new EntityNotFoundException("Ad not found with id: {0}", dto.getId()));
        advertisementDtoMapper.update(exAd, newAd);
        advertisementRepository.update(exAd);
    }

    @Override
    public void delete(Long id) {
        Advertisement advertisement = advertisementRepository.get(id).orElseThrow(() -> new EntityNotFoundException("Ad not found with id: {0}", id));
        advertisementRepository.delete(id);
    }
}
