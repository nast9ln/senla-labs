package ru.labs.coffer.controller;

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
import ru.labs.coffer.dto.CategoryDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.Category;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.mapper.CategoryMapper;
import ru.labs.coffer.mapper.PersonMapper;
import ru.labs.coffer.util.DataFactory;
import ru.labs.coffer.util.DatabaseUtil;
import ru.labs.coffer.util.TestJwtTokenProvider;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static ru.labs.coffer.common.GeneralConstant.AUTHORIZATION_HEADER;
import static ru.labs.coffer.util.DatabaseUtil.TEST_LOGIN;


@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdvertisementControllerTest extends BaseMock {
    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    @Autowired
    private DatabaseUtil databaseUtil;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private TestJwtTokenProvider provider;
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void testCreateAdvertisement() throws Exception {
        Person person = databaseUtil.createPerson();
        PersonDto personDto = personMapper.toDto(person);

        Category category = databaseUtil.createCategory();
        CategoryDto categoryDto = categoryMapper.toDto(category);

        AdvertisementDto expected = DataFactory.getAdvertisementDtoForTest(null);

        expected.setPerson(personDto);
        expected.setCategory(categoryDto);
        String token = provider.buildJwtToken(TEST_LOGIN);
        mockAuth(TEST_LOGIN);

        MvcResult mvcResult = mockMvc.perform(post("/advertisement")
                        .header(AUTHORIZATION_HEADER, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expected)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        AdvertisementDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AdvertisementDto.class);
        Assertions.assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void testReadAdvertisement() throws Exception {
        Advertisement expected = databaseUtil.createAdvertisement();

        String token = provider.buildJwtToken(TEST_LOGIN);

        mockAuth(TEST_LOGIN);

        MvcResult mvcResult = mockMvc.perform(get("/advertisement/{id}", expected.getId())
                        .header(AUTHORIZATION_HEADER, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        AdvertisementDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AdvertisementDto.class);
        Assertions.assertEquals(actual.getDescription(), expected.getDescription());
    }

    @Test
    public void testUpdateAdvertisement() throws Exception {
        Advertisement advertisement = databaseUtil.createAdvertisement();
        AdvertisementDto expected = DataFactory.getAdvertisementDtoForTest(advertisement.getId());

        String token = provider.buildJwtToken(TEST_LOGIN);

        mockAuth(TEST_LOGIN);

        MvcResult mvcResult = mockMvc.perform(put("/advertisement")
                        .header(AUTHORIZATION_HEADER, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expected)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

        mvcResult = mockMvc.perform(get("/advertisement/{id}", expected.getId())
                        .header(AUTHORIZATION_HEADER, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        AdvertisementDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AdvertisementDto.class);

        Assertions.assertEquals(expected.getDescription(), actual.getDescription());
    }


    @Test
    public void testDeleteAdvertisement() throws Exception {
        Long id = databaseUtil.createAdvertisement().getId();
        String token = provider.buildJwtToken(TEST_LOGIN);

        mockAuth(TEST_LOGIN);

        MvcResult mvcResult = mockMvc.perform(delete("/advertisement/{id}", id)
                        .header(AUTHORIZATION_HEADER, token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }
}