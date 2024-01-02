package com.example.demo.service.impl;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Person;
import com.example.demo.exception.EmptyException;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.exception.RelativeNotFoundException;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.AdvertisementService;
import com.example.demo.service.mapper.AdvertisementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("The user for the ad was not found with the id: {0}", personId));
        advertisement.setPerson(person);
        return advertisementDtoMapper.toDto(advertisementRepository.save(advertisement));
    }

    @Override
    public AdvertisementDto read(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ad not found with id: {0}", id));
        return advertisementDtoMapper.toDto(advertisement);
    }

    @Override
    public void update(AdvertisementDto dto) {
        Advertisement newAd = advertisementDtoMapper.toEntity(dto);
        Advertisement exAd = advertisementRepository.findById(dto.getId()).orElseThrow(() -> new EntityNotFoundException("Ad not found with id: {0}", dto.getId()));
        advertisementDtoMapper.update(exAd, newAd);
        advertisementRepository.save(exAd);
    }

    @Override
    public void delete(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ad not found with id: {0}", id));
        advertisement.setDeleted(true);
        advertisementRepository.save(advertisement);
    }

    @Override
    public boolean deleteByPersonId(Long id) {
        List<Advertisement> advertisements = advertisementRepository.findByPersonId(id);
        List<Advertisement> forSave = advertisements.stream().peek(ad -> ad.setDeleted(true)).collect(Collectors.toList());
        advertisementRepository.saveAll(forSave);
        return true;
    }

    @Override
    public Page<AdvertisementDto> findAllOrderedByTopAndCreatedDate(Pageable pageable) {
        Page<Advertisement> advertisements = advertisementRepository.findAllOrderedByTopAndCreatedDate(pageable);
        return advertisements.map(advertisementDtoMapper::toDto);
    }

    @Override
    public Page<AdvertisementDto> findAllByCategoryId(Long id, Pageable pageable) {
        Page<Advertisement> advertisements = advertisementRepository.findAllByCategoryIdOrderByTopParamIdDescCreatedDateDesc(id, pageable);
        return advertisements.map(advertisementDtoMapper::toDto);
    }

    @Override
    public Page<AdvertisementDto> findAllByCostLessThan(Integer cost, Pageable pageable) {
        Page<Advertisement> advertisements = advertisementRepository.findAllByCostLessThanOrderByTopParamIdDescCreatedDateDesc(cost, pageable);
        if (advertisements.stream().toList().isEmpty()){
            throw new EmptyException("There are no advertisements whose price id lower then this price: {0}", cost);
        }
        return advertisements.map(advertisementDtoMapper::toDto);
    }

    @Override
    public Page<AdvertisementDto> findAllByCostGreaterThan(Integer cost, Pageable pageable) {
        Page<Advertisement> advertisements = advertisementRepository.findAllByCostGreaterThanOrderByTopParamIdDescCreatedDateDesc(cost, pageable);
        if (advertisements.stream().toList().isEmpty()){
            throw new EmptyException("There are no advertisements whose price id greater then this price: {0}", cost);
        }
        return advertisements.map(advertisementDtoMapper::toDto);
    }

    public List<AdvertisementDto> findByPersonId (Long id) {
        return advertisementRepository.findByPersonId(id).stream().map(advertisementDtoMapper::toDto).collect(Collectors.toList());
    }

}