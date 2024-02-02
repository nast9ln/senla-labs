package ru.labs.coffer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import ru.labs.coffer.dto.CommentDto;

@Tag(name = "Контроллер комментария")
public interface CommentController {
    @Operation(summary = "Создание комментария")
    ResponseEntity<CommentDto> create(@Valid CommentDto commentDto);

    @Operation(summary = "Удаление комментария")
    ResponseEntity<Void> delete(Long id);
    @Operation(summary = "Получение всех комментариев к объявлению")
    ResponseEntity<Page<CommentDto>> findAllByAdvertisementId(Long id, Pageable pageable);
}
