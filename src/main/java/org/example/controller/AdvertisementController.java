package org.example.controller;

import org.example.dto.AdvertisementDto;

public interface AdvertisementController {

    AdvertisementDto create(AdvertisementDto dto);

    AdvertisementDto read(Long id);

    AdvertisementDto update(AdvertisementDto dto);

    void delete(Long id);

}
