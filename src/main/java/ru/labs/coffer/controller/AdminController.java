package ru.labs.coffer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import ru.labs.coffer.dto.RoleDto;
import ru.labs.coffer.dto.TopParamDto;

import java.util.Set;

@Tag(name = "Контроллер админа")
public interface AdminController {
    @Operation(summary = "Изменение роли пользователя")
    ResponseEntity<Void> changePersonRole(Long id, Set<RoleDto> roles);

    @Operation(summary = "Добавление объявления в топ")
    ResponseEntity<Void> addTopParam(Long advertisementId, TopParamDto topParamDto);

    @Operation(summary = "Удаление пользователя")
    ResponseEntity<Void> deletePerson(Long id);

    @Operation(summary = "Удаление комментария")
    ResponseEntity<Void> deleteComment(Long id);

    @Operation(summary = "Удаление объявления")
    ResponseEntity<Void> deleteAdvertisement(Long id);

    @Operation(summary = "Изменение параметров топа")
    ResponseEntity<Void> changeTopParam(TopParamDto topParamDto);
}
