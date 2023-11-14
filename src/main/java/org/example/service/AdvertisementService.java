package org.example.service;

import org.example.dto.AdvertisementDto;

public interface AdvertisementService {

    AdvertisementDto create(AdvertisementDto dto);

    AdvertisementDto read(Long id);

    AdvertisementDto update(AdvertisementDto dto);

    void delete(Long id);

}
