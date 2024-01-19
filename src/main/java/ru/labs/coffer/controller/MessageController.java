package ru.labs.coffer.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.labs.coffer.dto.MessageDto;
import ru.labs.coffer.dto.PersonDto;

public interface MessageController {
    Page<MessageDto> getDialog(Long advertisementId, Pageable pageable);

    void sendMessage(MessageDto messageDto);

    Page<PersonDto> getPersonsWithDialogsByCreator(Pageable pageable);
    Page<MessageDto> getDialogByCreator(Long advertisementId, Long personId, Pageable pageable);
}
