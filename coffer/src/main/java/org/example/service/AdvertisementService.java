package org.example.service;

import org.example.dto.AdvertisementDto;

public interface AdvertisementService {

    void create(AdvertisementDto dto);

    AdvertisementDto read(Long id);

    void update(AdvertisementDto dto);

    void delete(Long id);

}
