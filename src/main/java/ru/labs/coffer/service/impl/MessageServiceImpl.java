package ru.labs.coffer.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.labs.coffer.dto.MessageDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.dto.security.JwtPerson;
import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.Message;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.mapper.AdvertisementMapper;
import ru.labs.coffer.mapper.MessageMapper;
import ru.labs.coffer.mapper.PersonMapper;
import ru.labs.coffer.repository.AdvertisementRepository;
import ru.labs.coffer.repository.MessageRepository;
import ru.labs.coffer.repository.PersonRepository;
import ru.labs.coffer.service.MessageService;
import ru.labs.coffer.service.security.JwtAuthorizationService;

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
    public Page<MessageDto> getDialog(Long advertisementId, Long personId, Pageable pageable) {
        JwtPerson jwtPerson = jwtAuthorizationService.extractJwtPerson();
        log.info("Get dialog between person with id {} in advertisement with id {}", jwtPerson.getId(), advertisementId);
        Page<Message> dialog = messageRepository.findDialogByCreator(advertisementId, jwtPerson.getId(), personId, pageable);
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

    @Override
    public Page<PersonDto> getPersonsWithDialogsByCreator(Pageable pageable) {
        JwtPerson jwtPerson = jwtAuthorizationService.extractJwtPerson();
        Page<Person> persons = messageRepository.findPersonsWithDialogByCreator(jwtPerson.getId(), pageable);
        return persons.map(personMapper::toDto);
    }
}
