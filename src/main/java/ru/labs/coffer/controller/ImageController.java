package ru.labs.coffer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import ru.labs.coffer.dto.ImageDto;

@Tag(name = "Контроллер изображения")
public interface ImageController {
    @Operation(summary = "Создание изображения")
    ResponseEntity<Void> create(ImageDto dto);

    @Operation(summary = "Удаление объявления")
    ResponseEntity<Void> delete(Long id);
}
