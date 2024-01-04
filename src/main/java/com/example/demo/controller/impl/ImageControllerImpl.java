package com.example.demo.controller.impl;

import com.example.demo.controller.ImageController;
import com.example.demo.dto.ImageDto;
import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("{id}")
    public ImageDto read(@PathVariable Long id) {
        return imageService.read(id);
    }

    @PutMapping
    public void update(@RequestBody ImageDto dto) {
        imageService.update(dto);
    }

    @DeleteMapping("/{image-id}")
    public void delete(@PathVariable("image-id") Long id) {
        imageService.delete(id);
    }

    @DeleteMapping("/delete-by-adv-id/{adv-id}")
    public void deleteByAdvertisementId(@PathVariable("adv-id") Long id) {
        imageService.deleteByAdvertisementId(id);
    }

}
