package com.example.demo.controller.impl;

import com.example.demo.controller.MessageController;
import com.example.demo.dto.MessageDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@Slf4j
public class MessageControllerImpl implements MessageController {
    private final MessageService messageService;

    @GetMapping
    public Page<MessageDto> getDialog(@RequestParam(name = "advertisementId") Long advertisementId, Pageable pageable) {
        return messageService.getDialog(advertisementId, pageable);
    }

    @Override
    @PostMapping
    public void sendMessage(@RequestBody MessageDto messageDto) {
        messageService.sendMessage(messageDto);
    }

    @Override
    @GetMapping("/creator")
    public Page<PersonDto> getPersonsWithDialogsByCreator(Pageable pageable) {
        return messageService.getPersonsWithDialogsByCreator(pageable);
    }

    @GetMapping("/byCreator")
    public Page<MessageDto> getDialogByCreator (@RequestParam(name = "advertisementId") Long advertisementId, @RequestParam(name = "personId") Long personId, Pageable pageable){
        return messageService.getDialog(advertisementId, personId, pageable);
    }
}
