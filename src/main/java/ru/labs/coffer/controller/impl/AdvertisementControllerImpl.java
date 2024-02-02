package ru.labs.coffer.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.labs.coffer.controller.AdvertisementController;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.search.AdvertisementSearchDto;
import ru.labs.coffer.service.AdvertisementService;

@Validated
@RestController
@RequestMapping("/advertisement")
@RequiredArgsConstructor
public class AdvertisementControllerImpl implements AdvertisementController {

    private final AdvertisementService advertisementService;

    @Override
    @PostMapping
    public ResponseEntity<AdvertisementDto> create(@RequestBody AdvertisementDto dto) {
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
    @GetMapping("/history/{id}")
    public ResponseEntity<Page<AdvertisementDto>> getHistoryByPersonId(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(advertisementService.getHistoryByPersonId(id, pageable));
    }

    @Override
    @PostMapping("/search")
    public ResponseEntity<Page<AdvertisementDto>> findAll(@RequestBody AdvertisementSearchDto advertisementSearchDto) {
        return ResponseEntity.ok(advertisementService.findAll(advertisementSearchDto));
    }


}
