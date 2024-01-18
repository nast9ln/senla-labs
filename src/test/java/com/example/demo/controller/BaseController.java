package com.example.demo.controller;

import com.example.demo.dto.security.AuthenticationResponse;
import com.example.demo.dto.security.JwtPerson;
import com.example.demo.mapper.AdvertisementMapper;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    @Autowired
    protected CategoryRepository categoryRepository;
    @Autowired
    protected CategoryMapper categoryMapper;


    public AuthenticationResponse register(String login) throws Exception {
        String authDto = mapper.writeValueAsString(
                JwtPerson.builder()
                        .login(login)
                        .password("test")
                        .build()
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(authDto))
                .andExpect(MockMvcResultMatchers.status().isOk());
        return login(login);
    }


    protected AuthenticationResponse login(String login) throws Exception {
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
}
