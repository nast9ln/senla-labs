package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AdvertisementDto;
import org.example.service.AdvertisementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/advertisement")
@RequiredArgsConstructor
public class AdvertisementControllerImpl implements AdvertisementController {

    private final AdvertisementService advertisementService;

    @Override
    @PostMapping
    public AdvertisementDto create(AdvertisementDto dto) {
        return advertisementService.create(dto);
    }

    @Override
    @GetMapping
    public AdvertisementDto read(@RequestParam("id") Long id) {
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
