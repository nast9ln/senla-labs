package com.example.demo.controller;

import com.example.demo.dto.ImageDto;

public interface ImageController {
    void create(ImageDto dto);

    void delete(Long id);

    void deleteByAdvertisementId(Long id);
}
