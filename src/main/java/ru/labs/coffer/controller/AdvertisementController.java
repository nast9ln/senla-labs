package ru.labs.coffer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.CategoryDto;
import ru.labs.coffer.dto.PageInfo;
import ru.labs.coffer.dto.search.AdvertisementSearchDto;

import java.util.List;

@Tag(name = "Контроллер объявления")
public interface AdvertisementController {
    @Operation(summary = "Создание объявления")
    ResponseEntity<AdvertisementDto> create(@Valid AdvertisementDto dto);

    @Operation(summary = "Получение объявления")
    ResponseEntity<AdvertisementDto> read(Long id);

    @Operation(summary = "Обновление объявления")
    ResponseEntity<Void> update(AdvertisementDto dto);

    @Operation(summary = "Удаление объявления")
    ResponseEntity<Void> delete(Long id);

    @Operation(summary = "Получение истории объявлений пользователя")
    ResponseEntity<Page<AdvertisementDto>> getHistoryByPersonId(Long id, Pageable pageable);

    @Operation(summary = "Получение всех отсортированных объявления, которые могут содержать фильтр")
    ResponseEntity<Page<AdvertisementDto>> findAll(AdvertisementSearchDto pageable);

}