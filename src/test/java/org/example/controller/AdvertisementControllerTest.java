package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.TestConnectionConfig;
import org.example.dto.AdvertisementDto;
import org.example.dto.PersonDto;
import org.example.entity.Advertisement;
import org.example.entity.Person;
import org.example.service.mapper.PersonMapper;
import org.example.util.DataFactory;
import org.example.util.DatabaseUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {TestConnectionConfig.class}
)
@WebAppConfiguration
public class AdvertisementControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private MockMvc mockMvc;
    @Autowired
    private DatabaseUtil databaseUtil;
    @Autowired
    private PersonMapper personMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();
    }

    @Test
    public void testCreateAdvertisement() throws Exception {
        Person person = databaseUtil.createPerson();
        PersonDto personDto = personMapper.toDto(person);

        AdvertisementDto expected = DataFactory.getAdvertisementDtoForTest(null);
        expected.setPerson(personDto);
        MvcResult mvcResult = mockMvc.perform(post("/advertisement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expected)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        AdvertisementDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),AdvertisementDto.class);
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void testReadAdvertisement() throws Exception {
        Advertisement expected = databaseUtil.createAdvertisement();

        MvcResult mvcResult = mockMvc.perform(get("/advertisement/{id}", expected.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        AdvertisementDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AdvertisementDto.class);
        Assert.assertEquals(actual.getDescription(), expected.getDescription());
    }

    @Test
    public void testUpdateAdvertisement() throws Exception {
        Advertisement advertisement = databaseUtil.createAdvertisement();
        AdvertisementDto expected = DataFactory.getAdvertisementDtoForTest(advertisement.getId());

        MvcResult mvcResult = mockMvc.perform(put("/advertisement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expected)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(200, mvcResult.getResponse().getStatus());

        mvcResult = mockMvc.perform(get("/advertisement/{id}", expected.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        AdvertisementDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), AdvertisementDto.class);

        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }


    @Test
    public void testDeleteAdvertisement () throws Exception {
        Long id = databaseUtil.createAdvertisement().getId();
        MvcResult mvcResult= mockMvc.perform(delete("/advertisement/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
