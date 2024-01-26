package ru.labs.coffer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.PageInfo;
import ru.labs.coffer.dto.TopParamDto;
import ru.labs.coffer.dto.search.AdvertisementSearchDto;
import ru.labs.coffer.entity.Advertisement;

import java.util.List;

public interface AdvertisementService {

    AdvertisementDto create(AdvertisementDto dto);

    AdvertisementDto read(Long id);

    void update(AdvertisementDto dto);

    void delete(Long id);

    boolean deleteByPersonId(Long id);
    void addTopParam(Long advertisementId, TopParamDto topParamDto);

    Page<AdvertisementDto> getHistoryByPersonId(Long id, Pageable pageable);

    Page<AdvertisementDto> findAll(AdvertisementSearchDto pageable);
}
