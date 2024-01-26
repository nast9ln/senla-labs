package ru.labs.coffer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.CategoryDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.dto.TopParamDto;
import ru.labs.coffer.dto.search.AdvertisementSearchDto;
import ru.labs.coffer.dto.security.JwtPerson;
import ru.labs.coffer.entity.*;
import ru.labs.coffer.exception.EntityNotFoundException;
import ru.labs.coffer.exception.RelativeNotFoundException;
import ru.labs.coffer.mapper.AdvertisementMapper;
import ru.labs.coffer.mapper.TopParamMapper;
import ru.labs.coffer.repository.AdvertisementRepository;
import ru.labs.coffer.repository.CategoryRepository;
import ru.labs.coffer.repository.PersonRepository;
import ru.labs.coffer.repository.TopParamRepository;
import ru.labs.coffer.service.AdvertisementService;
import ru.labs.coffer.service.security.JwtAuthorizationService;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.labs.coffer.util.SpecificationUtils.*;

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
    private final JwtAuthorizationService jwtAuthorizationService;

    @Override
    public AdvertisementDto create(AdvertisementDto dto) {
        dto.setId(null);
        Advertisement advertisement = advertisementDtoMapper.toEntity(dto);
        JwtPerson jwtPerson = jwtAuthorizationService.extractJwtPerson();

        Long personId = jwtPerson.getId();
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
        advertisement.setIsDeleted(false);

        if (Objects.nonNull(dto.getTopParam().getTimeInTop())) {
            advertisement.setTopParam(new TopParam(Instant.now(), dto.getTopParam().getTimeInTop()));
        }

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
        advertisement.setIsDeleted(true);
        advertisementRepository.save(advertisement);
    }

    @Override
    public boolean deleteByPersonId(Long id) {
        List<Advertisement> advertisements = advertisementRepository.findByPersonId(id);
        List<Advertisement> forSave = advertisements.stream().peek(ad -> ad.setIsDeleted(true)).collect(Collectors.toList());
        advertisementRepository.saveAll(forSave);
        return true;
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

    @Override
    public Page<AdvertisementDto> getHistoryByPersonId(Long id, Pageable pageable) {
        Page<Advertisement> advertisements = advertisementRepository.getHistoryByPersonId(id, pageable);
        return advertisements.map(advertisementDtoMapper::toDto);
    }

    @Override
    public Page<AdvertisementDto> findAll(AdvertisementSearchDto searchDto) {
        Page<Advertisement> advertisements = advertisementRepository.findAll(getSpecification(searchDto), searchDto.getPageable());
        return advertisements.map(advertisementDtoMapper::toDto);
    }

    private Specification<Advertisement> getSpecification(AdvertisementSearchDto searchDto) {
        return searchDto.<Advertisement>getBaseSpecification()
                .and(between(Advertisement_.cost, searchDto.getMinCost(), searchDto.getMaxCost()))
                .and(is(createPath(Advertisement_.category, Category_.id), searchDto.getCategoryId()))
                .and(is(createPath(Advertisement_.person, Person_.id), searchDto.getPersonId()))
                .and(insensitiveLike(Advertisement_.city, searchDto.getCity()))
                .and(insensitiveLike(Advertisement_.header, searchDto.getHeader()))
                ;
    }

}