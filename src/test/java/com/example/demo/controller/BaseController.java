package com.example.demo.controller;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.dto.TopParamDto;
import com.example.demo.dto.security.AuthenticationResponse;
import com.example.demo.dto.security.JwtPerson;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Category;
import com.example.demo.entity.Person;
import com.example.demo.entity.TopParam;
import com.example.demo.enums.CategoryType;
import com.example.demo.mapper.AdvertisementMapper;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class BaseController {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper mapper;
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected PersonRepository personRepository;
    @Autowired
    protected AdvertisementRepository advertisementRepository;
    @Autowired
    protected PersonMapper personMapper;
    @Autowired
    protected AdvertisementMapper advertisementMapper;


    public AuthenticationResponse register (String login) throws Exception {
        String authDto = mapper.writeValueAsString(
                JwtPerson.builder()
                        .login(login)
                        .password("test")
                   //     .isDeleted(false)
                        .build()
        );
        mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authDto))
                .andExpect(MockMvcResultMatchers.status().isOk());
        return login(login);
    }


    protected AuthenticationResponse login (String login) throws Exception {
        String person = mapper.writeValueAsString(
                JwtPerson.builder()
                        .login(login)
                        .password("test")
                        .isDeleted(false)
                        .build());
        String response = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(person))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        AuthenticationResponse authPerson = mapper.readValue(response, AuthenticationResponse.class);
        Assertions.assertFalse(authPerson.getToken().isEmpty());
        return authPerson;
    }

    protected AdvertisementDto createAdvertisement(String login) throws Exception {
        register(login);

        Optional<Person> person = personRepository.findByLogin(login);
        PersonDto personDto = personMapper.toDto(person.get());


        AdvertisementDto advertisementDto = AdvertisementDto.builder()
                .category(CategoryDto.builder()
                        .name(CategoryType.CLOTHING.name())
                        .build())
                .createdDate(Instant.now())
                .city("CityTest")
                .person(personDto)
                .topParam(TopParamDto
                        .builder()
                        .timeTopStart(Instant.now())
                        .timeInTop(720000)
                        .isTop(true)
                        .build())
                .cost(100)
                .header("Test header")
                .description("Test description")
                .isDeleted(false)
                .build();

        advertisementRepository.save(advertisementMapper.toEntity(advertisementDto));
        return advertisementDto;

    }
}
