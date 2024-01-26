package ru.labs.coffer.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.labs.coffer.controller.MessageController;
import ru.labs.coffer.dto.MessageDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.dto.search.MessageSearchDto;
import ru.labs.coffer.service.MessageService;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageControllerImpl implements MessageController {
    private final MessageService messageService;

    @Override
    @PostMapping("/dialog")
    public ResponseEntity<Page<MessageDto>> getDialog(@RequestBody MessageSearchDto searchDto) {
        return ResponseEntity.ok(messageService.getDialog(searchDto));
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody MessageDto messageDto) {
        messageService.sendMessage(messageDto);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/creator")
    public ResponseEntity<Page<PersonDto>> getPersonsWithDialogsByCreator(Pageable pageable) {
        return ResponseEntity.ok(messageService.getPersonsWithDialogsByCreator(pageable));
    }

    @Override
    @GetMapping("/by-creator/{advertisementId}/{personId}")
    public ResponseEntity<Page<MessageDto>> getDialogByCreator(@PathVariable Long advertisementId, @PathVariable Long personId, Pageable pageable) {
        return ResponseEntity.ok(messageService.getDialog(advertisementId, personId, pageable));
    }
}
