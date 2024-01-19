package ru.labs.coffer.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import ru.labs.coffer.dto.MessageDto;
import ru.labs.coffer.dto.PersonDto;

public interface MessageController {
    ResponseEntity<Page<MessageDto>> getDialog(Long advertisementId, Pageable pageable);

    ResponseEntity<Void> sendMessage(MessageDto messageDto);

    ResponseEntity<Page<PersonDto>> getPersonsWithDialogsByCreator(Pageable pageable);
    ResponseEntity<Page<MessageDto>> getDialogByCreator(Long advertisementId, Long personId, Pageable pageable);
}
