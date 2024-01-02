package com.example.demo.service;

import com.example.demo.dto.ImageDto;

public interface ImageService {
    void create(ImageDto imageDto);
    ImageDto read (Long id);
    void update(Long id, ImageDto imageDto);
    void delete(Long id);
    void deleteByAdvertisementId(Long id);
}
