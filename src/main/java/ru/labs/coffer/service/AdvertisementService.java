package ru.labs.coffer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.TopParamDto;

import java.util.List;

public interface AdvertisementService {

    AdvertisementDto create(AdvertisementDto dto);

    AdvertisementDto read(Long id);

    void update(AdvertisementDto dto);

    void delete(Long id);

    boolean deleteByPersonId(Long id);

    Page<AdvertisementDto> findAllOrderedByTopAndCreatedDate(Pageable pageable);

    Page<AdvertisementDto> findAllByCategoryId(Long id, Pageable pageable);

    Page<AdvertisementDto> findAllByCostLessThan(Integer cost, Pageable pageable);

    Page<AdvertisementDto> findAllByCostGreaterThan(Integer cost, Pageable pageable);


    List<AdvertisementDto> findByPersonId(Long id);

    void addTopParam(Long advertisementId, TopParamDto topParamDto);
}
