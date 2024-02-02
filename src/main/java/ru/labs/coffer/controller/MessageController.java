package ru.labs.coffer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import ru.labs.coffer.dto.MessageDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.dto.search.MessageSearchDto;

@Tag(name = "Контроллер сообщения")
public interface MessageController {
    @Operation(summary = "Получение диалога авторизованного пользователя с пользователем-создателем объявления")
    ResponseEntity<Page<MessageDto>> getDialog(MessageSearchDto searchDto);

    @Operation(summary = "Отправка сообщения")
    ResponseEntity<Void> sendMessage(MessageDto messageDto);

    @Operation(summary = "Получение создателем объявления пользователей, имеющих переписку с ним")
    ResponseEntity<Page<PersonDto>> getPersonsWithDialogsByCreator(Pageable pageable);

    @Operation(summary = "Получение диалога создателем объявления с пользователем")
    ResponseEntity<Page<MessageDto>> getDialogByCreator(Long advertisementId, Long personId, Pageable pageable);
}
