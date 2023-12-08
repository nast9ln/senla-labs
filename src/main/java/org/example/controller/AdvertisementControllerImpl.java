package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AdvertisementDto;
import org.example.service.AdvertisementService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/advertisement")
@RequiredArgsConstructor
public class AdvertisementControllerImpl implements AdvertisementController {

    private final AdvertisementService advertisementService;

    @Override
    @PostMapping
    public AdvertisementDto create(@Valid @RequestBody AdvertisementDto dto) {
        return advertisementService.create(dto);
    }

    @Override
    @GetMapping("/{id}")
    public AdvertisementDto read(@PathVariable Long id) {
        return advertisementService.read(id);
    }

    @Override
    @Transactional
    @PutMapping
    public void update(@RequestBody AdvertisementDto dto) {
        advertisementService.update(dto);
    }

    @Override
    @DeleteMapping("/{advertisement-id}")
    public void delete(@PathVariable("advertisement-id") Long id) {
        advertisementService.delete(id);
    }
}
