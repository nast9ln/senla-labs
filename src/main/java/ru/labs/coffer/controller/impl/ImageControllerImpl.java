package ru.labs.coffer.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.labs.coffer.controller.ImageController;
import ru.labs.coffer.dto.ImageDto;
import ru.labs.coffer.service.ImageService;

@RestController("/image")
@RequiredArgsConstructor
public class ImageControllerImpl implements ImageController {
    private final ImageService imageService;

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ImageDto dto) {
        imageService.create(dto);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{image-id}")
    public ResponseEntity<Void> delete(@PathVariable("image-id") Long id) {
        imageService.delete(id);
        return ResponseEntity.ok().build();
    }

}
