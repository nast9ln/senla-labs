package ru.labs.coffer.controller;

import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.MessageDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.mapper.AdvertisementMapper;
import ru.labs.coffer.mapper.PersonMapper;
import ru.labs.coffer.service.security.JwtService;
import ru.labs.coffer.util.DatabaseUtil;
import ru.labs.coffer.util.TestJwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.Instant;

import static ru.labs.coffer.common.GeneralConstant.AUTHORIZATION_HEADER;
import static ru.labs.coffer.util.DatabaseUtil.TEST_LOGIN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageControllerTest extends BaseMock {
    @Autowired
    private DatabaseUtil databaseUtil;
    @Autowired
    private TestJwtTokenProvider provider;
    @Autowired
    protected PersonMapper personMapper;
    @Autowired
    protected AdvertisementMapper advertisementMapper;

    @Test
    public void sendMessage() throws Exception {
        Person person = databaseUtil.createPerson();
        PersonDto personDto = personMapper.toDto(person);

        Advertisement advertisement = databaseUtil.createAdvertisement();
        AdvertisementDto advertisementDto = advertisementMapper.toDto(advertisement);

        MessageDto messageDto = createMessage();
        messageDto.setSenderId(personDto.getId());
        messageDto.setAdvertisementId(advertisementDto.getId());

        String token = provider.buildJwtToken(TEST_LOGIN);

        mockAuth(TEST_LOGIN);

        mockMvc.perform(post("/message")
                        .header(AUTHORIZATION_HEADER, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(messageDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());

    }

    private MessageDto createMessage() {
        MessageDto messageDto = MessageDto
                .builder()
                .text("test")
                .createdDate(Instant.now())
                .isDeleted(false)
                .build();
        return messageDto;
    }
}