package org.example.controller;

import org.example.dto.AdvertisementDto;

public interface AdvertisementController {

    AdvertisementDto create(AdvertisementDto dto);

    AdvertisementDto read(Long id);

    void update(AdvertisementDto dto);

    void delete(Long id);

}
