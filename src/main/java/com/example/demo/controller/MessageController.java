package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageController {
    Page<MessageDto> getDialog(Long advertisementId, Pageable pageable);
    void sendMessage(MessageDto messageDto);
    Page<PersonDto> getPersonsWithDialogsByCreator (Pageable pageable);
}
