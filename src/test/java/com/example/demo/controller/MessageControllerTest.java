package com.example.demo.controller;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.MessageDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Person;
import com.example.demo.service.security.JwtService;
import com.example.demo.util.DatabaseUtil;
import com.example.demo.util.TestJwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.Instant;

import static com.example.demo.util.DatabaseUtil.TEST_LOGIN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageControllerTest extends BaseController {
    @Autowired
    private DatabaseUtil databaseUtil;
    @Autowired
    private TestJwtTokenProvider provider;

    @MockBean
    private JwtService jwtService;

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

        when(jwtService.extractLogin(any())).thenReturn(TEST_LOGIN);
        when(jwtService.isTokenValid(any(), any())).thenReturn(true);

        mockMvc.perform(post("/message")
                        .header("Authorization", token)
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