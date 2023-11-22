package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AdvertisementDto;
import org.example.service.AdvertisementService;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AdvertisementControllerImpl implements AdvertisementController {

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
    public void update(AdvertisementDto dto) {
        advertisementService.update(dto);
    }

    @Override
    public void delete(Long id) {
        advertisementService.delete(id);
    }
}
