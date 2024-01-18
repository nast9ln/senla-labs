package com.example.demo.service.impl;

import com.example.demo.dto.MessageDto;
import com.example.demo.dto.security.JwtPerson;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Message;
import com.example.demo.entity.Person;
import com.example.demo.mapper.AdvertisementMapper;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.MessageService;
import com.example.demo.service.security.JwtAuthorizationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final PersonMapper personMapper;
    private final AdvertisementMapper advertisementMapper;
    private final MessageMapper messageMapper;
    private final PersonRepository personRepository;
    private final AdvertisementRepository advertisementRepository;
    private final JwtAuthorizationService jwtAuthorizationService;

    @Override
    public Page<MessageDto> getDialog(Long advertisementId, Pageable pageable) {
        JwtPerson jwtPerson = jwtAuthorizationService.extractJwtPerson();
        log.info("Get dialog between person with id {} in advertisement with id {}", jwtPerson.getId(), advertisementId);
        Page<Message> dialog = messageRepository.getDialog(jwtPerson.getId(), advertisementId, pageable);
        return dialog.map(messageMapper::toDto);
    }

    @Override
    public void sendMessage(MessageDto dto) {
        dto.setId(null);
        JwtPerson jwtPerson = jwtAuthorizationService.extractJwtPerson();
        log.info("Person with id {} send message in advertisement with id {}", jwtPerson.getId(), dto.getAdvertisementId());
        dto.setSenderId(jwtPerson.getId());
        Message message = messageMapper.toEntity(dto);
        Person person = personRepository.getReferenceById(jwtPerson.getId());
        message.setSender(person);
        Advertisement advertisement = advertisementRepository.getReferenceById(dto.getAdvertisementId());
        message.setAdvertisement(advertisement);
        messageRepository.save(message);
    }
}
