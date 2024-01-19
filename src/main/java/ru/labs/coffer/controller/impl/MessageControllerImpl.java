package ru.labs.coffer.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.labs.coffer.controller.MessageController;
import ru.labs.coffer.dto.MessageDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.service.MessageService;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@Slf4j
public class MessageControllerImpl implements MessageController {
    private final MessageService messageService;

    @GetMapping
    @Override
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

    @Override
    @GetMapping("/byCreator")
    public Page<MessageDto> getDialogByCreator(@RequestParam(name = "advertisementId") Long advertisementId, @RequestParam(name = "personId") Long personId, Pageable pageable) {
        return messageService.getDialog(advertisementId, personId, pageable);
    }
}
