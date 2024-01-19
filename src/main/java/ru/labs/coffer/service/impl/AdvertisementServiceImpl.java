package ru.labs.coffer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.CategoryDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.dto.TopParamDto;
import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.Category;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.entity.TopParam;
import ru.labs.coffer.exception.EntityNotFoundException;
import ru.labs.coffer.exception.RelativeNotFoundException;
import ru.labs.coffer.mapper.AdvertisementMapper;
import ru.labs.coffer.mapper.TopParamMapper;
import ru.labs.coffer.repository.AdvertisementRepository;
import ru.labs.coffer.repository.CategoryRepository;
import ru.labs.coffer.repository.PersonRepository;
import ru.labs.coffer.repository.TopParamRepository;
import ru.labs.coffer.service.AdvertisementService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
    private final AdvertisementMapper advertisementDtoMapper;
    private final AdvertisementRepository advertisementRepository;
    private final PersonRepository personRepository;
    private final CategoryRepository categoryRepository;
    private final TopParamMapper topParamMapper;
    private final TopParamRepository topParamRepository;

    @Override
    public AdvertisementDto create(AdvertisementDto dto) {
        dto.setId(null);
        Advertisement advertisement = advertisementDtoMapper.toEntity(dto);

        Long personId = Optional.ofNullable(dto.getPerson()).map(PersonDto::getId).orElse(null);
        if (Objects.isNull(personId)) {
            throw new RelativeNotFoundException("Person id must be not null");
        }

        Long categoryId = Optional.ofNullable(dto.getCategory()).map(CategoryDto::getId).orElse(null);
        if (Objects.isNull(categoryId)) {
            throw new RelativeNotFoundException("Category id must be not null");
        }
        Person person = personRepository.getReferenceById(personId);
        advertisement.setPerson(person);

        Category category = categoryRepository.getReferenceById(categoryId);
        advertisement.setCategory(category);

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
        return advertisementRepository.findAllByCategoryIdOrderByTopParamIdDescCreatedDateDesc(id, pageable)
                .map(advertisementDtoMapper::toDto);
    }

    @Override
    public Page<AdvertisementDto> findAllByCostLessThan(Integer cost, Pageable pageable) {
        Page<Advertisement> advertisements = advertisementRepository.findAllByCostLessThanOrderByTopParamIdDescCreatedDateDesc(cost, pageable);
        return advertisements.map(advertisementDtoMapper::toDto);
    }

    @Override
    public Page<AdvertisementDto> findAllByCostGreaterThan(Integer cost, Pageable pageable) {
        Page<Advertisement> advertisements = advertisementRepository.findAllByCostGreaterThanOrderByTopParamIdDescCreatedDateDesc(cost, pageable);
        return advertisements.map(advertisementDtoMapper::toDto);
    }

    @Override
    public List<AdvertisementDto> findByPersonId(Long id) {
        return advertisementRepository.findByPersonId(id).stream().map(advertisementDtoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void addTopParam(Long advertisementId, TopParamDto topParamDto) {
        Advertisement advertisement = advertisementRepository.findById(advertisementId).orElseThrow(() -> new EntityNotFoundException("Ad not found with id: {0}", advertisementId));

        topParamDto.setId(null);
        TopParam topParam = topParamMapper.toEntity(topParamDto);
        topParamRepository.save(topParam);

        advertisement.setTopParam(topParamMapper.toEntity(topParamDto));
        advertisementRepository.save(advertisement);
    }

}