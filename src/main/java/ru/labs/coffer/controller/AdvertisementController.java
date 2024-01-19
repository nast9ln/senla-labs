package ru.labs.coffer.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import ru.labs.coffer.dto.AdvertisementDto;

import java.util.List;

public interface AdvertisementController {

    ResponseEntity<AdvertisementDto> create(@Valid AdvertisementDto dto);

    ResponseEntity<AdvertisementDto> read(Long id);

    ResponseEntity<Void> update(AdvertisementDto dto);

    ResponseEntity<Void> delete(Long id);

    ResponseEntity<Page<AdvertisementDto>> findAllOrderedByTopAndCreatedDate(Pageable pageable);

    ResponseEntity<Page<AdvertisementDto>> findAllByCategoryId(Long id, Pageable pageable);

    ResponseEntity<Page<AdvertisementDto>> findAllByCostLessThan(Integer cost, Pageable pageable);

    ResponseEntity<Page<AdvertisementDto>> findAllByCostGreaterThan(Integer cost, Pageable pageable);

    ResponseEntity<List<AdvertisementDto>> getAdvertisementByPersonId(Long id);

}