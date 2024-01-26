package ru.labs.coffer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.MessageDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.dto.RatingDto;
import ru.labs.coffer.dto.security.JwtPerson;
import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.mapper.AdvertisementMapper;
import ru.labs.coffer.mapper.PersonMapper;
import ru.labs.coffer.util.DataFactory;
import ru.labs.coffer.util.DatabaseUtil;
import ru.labs.coffer.util.TestJwtTokenProvider;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.labs.coffer.common.GeneralConstant.AUTHORIZATION_HEADER;
import static ru.labs.coffer.util.DatabaseUtil.TEST_LOGIN;


@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonControllerTest extends BaseMock {
    @Autowired
    private TestJwtTokenProvider provider;

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    @Autowired
    private DatabaseUtil databaseUtil;
    @Autowired
    protected PersonMapper personMapper;
    @Autowired
    protected AdvertisementMapper advertisementMapper;

    @Test
    public void testRegisterPerson() {
        JwtPerson expected = DataFactory.getJwtPersonForTest();

        Assertions.assertDoesNotThrow(() -> mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expected)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn());

    }

    @Test
    public void testReadPerson() throws Exception {
        Person expected = databaseUtil.createPerson();

        String token = provider.buildJwtToken(TEST_LOGIN);

        mockAuth(TEST_LOGIN);

        MvcResult mvcResult = mockMvc.perform(get("/person/{id}", expected.getId())
                        .header(AUTHORIZATION_HEADER, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        PersonDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PersonDto.class);
        Assertions.assertEquals(actual.getLastName(), expected.getLastName());
    }


    @Test
    public void testUpdatePerson() throws Exception {

        Person person = databaseUtil.createPerson();
        PersonDto expected = DataFactory.getPersonDtoForTest(TEST_LOGIN, person.getId());

        String token = provider.buildJwtToken(TEST_LOGIN);
        mockAuth(TEST_LOGIN);

        MvcResult mvcResult = mockMvc.perform(put("/person")
                        .header(AUTHORIZATION_HEADER, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expected)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

        mvcResult = mockMvc.perform(get("/person/{id}", expected.getId())
                        .header(AUTHORIZATION_HEADER, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        PersonDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PersonDto.class);

        Assertions.assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    public void testDeletePerson() throws Exception {
        Long id = databaseUtil.createPerson().getId();

        String token = provider.buildJwtToken(TEST_LOGIN);

        mockAuth(TEST_LOGIN);

        MvcResult mvcResult = mockMvc.perform(delete("/person")
                        .header(AUTHORIZATION_HEADER, token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

    }

    @Test
    public void testRatePerson() throws Exception {
        Person person = databaseUtil.createPerson();
        PersonDto personDto = personMapper.toDto(person);

        Advertisement advertisement = databaseUtil.createAdvertisement();
        AdvertisementDto advertisementDto = advertisementMapper.toDto(advertisement);

        MessageDto messageDto = DataFactory.createMessage();
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

        Long evaluatorId = databaseUtil.createPerson().getId();
        RatingDto ratingDto = DataFactory.getRatingDtoForTest(advertisement.getPerson().getId(), 3);

        MvcResult mvcResult = mockMvc.perform(put("/person/rate")
                        .header(AUTHORIZATION_HEADER, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ratingDto)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

    }
}
