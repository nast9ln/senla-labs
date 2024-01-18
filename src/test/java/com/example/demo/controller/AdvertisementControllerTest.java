package com.example.demo.controller;

import com.example.demo.dto.AdvertisementDto;
import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Category;
import com.example.demo.entity.Person;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.security.JwtService;
import com.example.demo.util.DataFactory;
import com.example.demo.util.DatabaseUtil;
import com.example.demo.util.TestJwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import static com.example.demo.util.DatabaseUtil.TEST_LOGIN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdvertisementControllerTest extends BaseController {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    @Autowired
    private DatabaseUtil databaseUtil;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private TestJwtTokenProvider provider;
    @Autowired
    private CategoryRepository categoryRepository;

    @MockBean
    private JwtService jwtService;

    @Test
    public void testCreateAdvertisement() throws Exception {
        Person person = databaseUtil.createPerson();
        PersonDto personDto = personMapper.toDto(person);

        Category category = databaseUtil.createCategory();
        CategoryDto categoryDto = categoryMapper.toDto(category);
        System.out.println(categoryDto.getName());

        AdvertisementDto expected = DataFactory.getAdvertisementDtoForTest(null);

        expected.setPerson(personDto);
        expected.setCategory(categoryDto);
        System.out.println(expected.getCategory());
        System.out.println(expected.getPerson());
        System.out.println(expected);
        String token = provider.buildJwtToken(TEST_LOGIN);
        when(jwtService.extractLogin(any())).thenReturn(TEST_LOGIN);
        when(jwtService.isTokenValid(any(), any())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(post("/advertisement")
                        .header("Authorization", token)
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

        when(jwtService.extractLogin(any())).thenReturn(TEST_LOGIN);
        when(jwtService.isTokenValid(any(), any())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(get("/advertisement/{id}", expected.getId())
                        .header("Authorization", token)
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

        when(jwtService.extractLogin(any())).thenReturn(TEST_LOGIN);
        when(jwtService.isTokenValid(any(), any())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(put("/advertisement")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expected)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

        mvcResult = mockMvc.perform(get("/advertisement/{id}", expected.getId())
                        .header("Authorization", token)
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

        when(jwtService.extractLogin(any())).thenReturn(TEST_LOGIN);
        when(jwtService.isTokenValid(any(), any())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(delete("/advertisement/{id}", id)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }
}