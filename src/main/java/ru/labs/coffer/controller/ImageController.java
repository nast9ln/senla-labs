package ru.labs.coffer.controller;

import ru.labs.coffer.dto.ImageDto;

public interface ImageController {
    void create(ImageDto dto);

    void delete(Long id);

    void deleteByAdvertisementId(Long id);
}
