package ru.labs.coffer.controller.impl;

import lombok.RequiredArgsConstructor;
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
    public void create(@RequestBody ImageDto dto) {
        imageService.create(dto);
    }

    @Override
    @DeleteMapping("/{image-id}")
    public void delete(@PathVariable("image-id") Long id) {
        imageService.delete(id);
    }

    @Override
    @DeleteMapping("/delete-by-adv-id/{adv-id}")
    public void deleteByAdvertisementId(@PathVariable("adv-id") Long id) {
        imageService.deleteByAdvertisementId(id);
    }

}
