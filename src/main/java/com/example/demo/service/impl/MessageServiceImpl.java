package com.example.demo.service.impl;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.MessageDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Message;
import com.example.demo.entity.Person;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.MessageService;
import com.example.demo.service.mapper.AdvertisementMapper;
import com.example.demo.service.mapper.MessageMapper;
import com.example.demo.service.mapper.PersonMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Page<MessageDto> getDialog(Long personId, Long advertisementId, Pageable pageable) {
        log.info("Get dialog between person with id {} in advertisement with id {}", personId, advertisementId);
        return messageRepository.getDialog(personId, advertisementId, pageable).map(messageMapper::toDto);
    }

    @Override
    public void sendMessage(MessageDto dto) {
        log.info("Person with id {} send message in advertisement with id {}", dto.getSender().getId(), dto.getAdvertisement().getId());
        dto.setId(null);
        Message message = messageMapper.toEntity(dto);
        Long personId = Optional.ofNullable(dto.getSender()).map(PersonDto::getId).orElse(null);
        Long advertisementId = Optional.ofNullable(dto.getAdvertisement()).map(AdvertisementDto::getId).orElse(null);
        Person person = personRepository.getReferenceById(personId);
        message.setSender(person);
        Advertisement advertisement = advertisementRepository.getReferenceById(advertisementId);
        message.setAdvertisement(advertisement);
        messageRepository.save(message);
    }
}
