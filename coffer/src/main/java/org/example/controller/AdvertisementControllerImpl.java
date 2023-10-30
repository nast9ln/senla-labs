package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AdvertisementDto;
import org.example.service.AdvertisementService;
import org.example.service.CRUD;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AdvertisementControllerImpl implements CRUD<AdvertisementDto> {

    private final AdvertisementService advertisementService;

    @Override
    public AdvertisementDto create(AdvertisementDto dto) {
        return advertisementService.create(dto);
    }

    @Override
    public AdvertisementDto read(Long id) {
        return advertisementService.read(id);
    }

    @Override
    public AdvertisementDto update(AdvertisementDto dto) {
        return advertisementService.update(dto);
    }

    @Override
    public void delete(Long id) {
        advertisementService.delete(id);
    }
}
