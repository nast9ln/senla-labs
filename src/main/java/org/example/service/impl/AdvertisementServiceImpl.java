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

import java.time.Instant;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
    private final AdvertisementMapper advertisementDtoMapper;
    private final AdvertisementRepository advertisementRepository;
    private final PersonRepository personRepository;

    @Override
    public AdvertisementDto create(AdvertisementDto dto) {
        if (Objects.isNull(dto.getPerson())) {
            throw new RelativeNotFoundException("При создании объявления пользователь не может быть null");
        }
        Advertisement advertisement = advertisementDtoMapper.toEntity(dto);
        Long personId = advertisement.getPerson().getId();
        Person person = personRepository.get(personId)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь для объявления не найден с id: {0}", personId));
        advertisement.setPerson(person);
        return advertisementDtoMapper.toDto(advertisementRepository.create(advertisement));
    }

    @Override
    public AdvertisementDto read(Long id) {
        Advertisement advertisement = advertisementRepository.get(id).orElseThrow();
        return advertisementDtoMapper.toDto(advertisement);
    }

    @Override
    public void update(AdvertisementDto dto) {
        if (Objects.isNull(dto.getPerson())) {
            throw new RelativeNotFoundException("При обновлении объявления пользователь не может быть null");
        }
        Advertisement advertisement = advertisementDtoMapper.toEntity(dto);
        advertisementRepository.update(advertisement);
    }

    @Override
    public void delete(Long id) {
        advertisementRepository.delete(id);
    }
}
