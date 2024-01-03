package com.example.demo.controller.impl;

import com.example.demo.controller.AdvertisementController;
import com.example.demo.dto.AdvertisementDto;
import com.example.demo.service.AdvertisementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping
    public void update(@RequestBody AdvertisementDto dto) {
        advertisementService.update(dto);
    }

    @Override
    @DeleteMapping("/{advertisement-id}")
    public void delete(@PathVariable("advertisement-id") Long id) {
        advertisementService.delete(id);
    }

    @Override
    @GetMapping("/page")
    public Page<AdvertisementDto> findAllOrderedByTopAndCreatedDate(Pageable pageable) {
        return advertisementService.findAllOrderedByTopAndCreatedDate(pageable);
    }

    @Override
    @GetMapping("/category/{id}")
    public Page<AdvertisementDto> findAllByCategoryId(@PathVariable Long id, Pageable pageable) {
        return advertisementService.findAllByCategoryId(id, pageable);
    }

    @Override
    @GetMapping("/cost-less/{cost}")
    public Page<AdvertisementDto> findAllByCostLessThan(@PathVariable Integer cost, Pageable pageable) {
        return advertisementService.findAllByCostLessThan(cost, pageable);
    }

    @Override
    @GetMapping("/cost-greater/{cost}")
    public Page<AdvertisementDto> findAllByCostGreaterThan(@PathVariable Integer cost, Pageable pageable) {
        return advertisementService.findAllByCostGreaterThan(cost, pageable);
    }

    @Override
    @GetMapping("/user/{id}")
    public List<AdvertisementDto> getAdvertisementByPersonId(@PathVariable Long id) {
        return advertisementService.findByPersonId(id);
    }
}
