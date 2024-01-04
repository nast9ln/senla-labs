package com.example.demo.service;

import com.example.demo.dto.ImageDto;

public interface ImageService {
    void create(ImageDto imageDto);

    ImageDto read(Long id);

    void update(ImageDto dto);

    void delete(Long id);

    void deleteByAdvertisementId(Long id);
}
