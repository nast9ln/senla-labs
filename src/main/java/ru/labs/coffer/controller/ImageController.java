package ru.labs.coffer.controller;

import org.springframework.http.ResponseEntity;
import ru.labs.coffer.dto.ImageDto;

public interface ImageController {
    ResponseEntity<Void> create(ImageDto dto);

    ResponseEntity<Void> delete(Long id);

    ResponseEntity<Void> deleteByAdvertisementId(Long id);
}
