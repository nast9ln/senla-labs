package ru.labs.coffer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.labs.coffer.dto.MessageDto;
import ru.labs.coffer.dto.PersonDto;

public interface MessageService {
    Page<MessageDto> getDialog(Long advertisementId, Pageable pageable);

    Page<MessageDto> getDialog(Long advertisementId, Long personId, Pageable pageable);

    void sendMessage(MessageDto message);

    Page<PersonDto> getPersonsWithDialogsByCreator(Pageable pageable);
}

