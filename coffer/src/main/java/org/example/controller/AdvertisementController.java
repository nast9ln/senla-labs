package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AdvertisementDto;
import org.example.service.AdvertisementService;
import org.example.service.CRUD;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AdvertisementController implements CRUD<AdvertisementDto> {

    private final AdvertisementService advertisementService;

    @Override
    public void create(AdvertisementDto dto) {
        advertisementService.create(dto);
    }

    @Override
    public AdvertisementDto read(Long id) {
        return advertisementService.read(id);
    }

    @Override
    public void update(AdvertisementDto dto) {
        advertisementService.update(dto);
    }

    @Override
    public void delete(Long id) {
        advertisementService.delete(id);
    }
}
