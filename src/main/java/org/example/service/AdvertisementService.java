package org.example.service;

import org.example.dto.AdvertisementDto;

public interface AdvertisementService {

    AdvertisementDto create(AdvertisementDto dto);

    AdvertisementDto read(Long id);

    void update(AdvertisementDto dto);

    void delete(Long id);

    boolean deleteByPersonId(Long id);

}
