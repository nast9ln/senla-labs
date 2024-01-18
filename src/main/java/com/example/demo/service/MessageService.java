package com.example.demo.service;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {
    Page<MessageDto> getDialog(Long advertisementId, Pageable pageable);
    Page<MessageDto> getDialog(Long advertisementId, Long personId, Pageable pageable);

    void sendMessage(MessageDto message);

    Page<PersonDto> getPersonsWithDialogsByCreator(Pageable pageable);
}

