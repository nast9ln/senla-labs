package ru.labs.coffer.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.labs.coffer.dto.AdvertisementDto;

import java.util.List;

public interface AdvertisementController {

    AdvertisementDto create(@Valid AdvertisementDto dto);

    AdvertisementDto read(Long id);

    void update(AdvertisementDto dto);

    void delete(Long id);

    Page<AdvertisementDto> findAllOrderedByTopAndCreatedDate(Pageable pageable);

    Page<AdvertisementDto> findAllByCategoryId(Long id, Pageable pageable);

    Page<AdvertisementDto> findAllByCostLessThan(Integer cost, Pageable pageable);

    Page<AdvertisementDto> findAllByCostGreaterThan(Integer cost, Pageable pageable);

    List<AdvertisementDto> getAdvertisementByPersonId(Long id);

}