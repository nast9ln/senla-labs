package ru.labs.coffer.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.labs.coffer.controller.AdvertisementController;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.service.AdvertisementService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/advertisement")
@RequiredArgsConstructor
public class AdvertisementControllerImpl implements AdvertisementController {

    private final AdvertisementService advertisementService;

    @Override
    @PostMapping
    public ResponseEntity<AdvertisementDto> create(@Valid @RequestBody AdvertisementDto dto) {
        return ResponseEntity.ok(advertisementService.create(dto));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDto> read(@PathVariable Long id) {
        return ResponseEntity.ok(advertisementService.read(id));
    }

    @Override
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AdvertisementDto dto) {
       advertisementService.update(dto);
       return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{advertisement-id}")
    public ResponseEntity<Void> delete(@PathVariable("advertisement-id") Long id) {
        advertisementService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<Page<AdvertisementDto>> findAllOrderedByTopAndCreatedDate(Pageable pageable) {
        return ResponseEntity.ok(advertisementService.findAllOrderedByTopAndCreatedDate(pageable));
    }

    @Override
    @GetMapping("/category/{id}")
    public ResponseEntity<Page<AdvertisementDto>> findAllByCategoryId(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(advertisementService.findAllByCategoryId(id, pageable));
    }

    @Override
    @GetMapping("/cost-less/{cost}")
    public ResponseEntity<Page<AdvertisementDto>> findAllByCostLessThan(@PathVariable Integer cost, Pageable pageable) {
        return ResponseEntity.ok(advertisementService.findAllByCostLessThan(cost, pageable));
    }

    @Override
    @GetMapping("/cost-greater/{cost}")
    public ResponseEntity<Page<AdvertisementDto>> findAllByCostGreaterThan(@PathVariable Integer cost, Pageable pageable) {
        return ResponseEntity.ok(advertisementService.findAllByCostGreaterThan(cost, pageable));
    }

    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity<List<AdvertisementDto>> getAdvertisementByPersonId(@PathVariable Long id) {
        return ResponseEntity.ok(advertisementService.findByPersonId(id));
    }
}
