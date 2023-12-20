package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.TestConnectionConfig;
import org.example.dto.PersonDto;
import org.example.entity.Person;
import org.example.enums.RoleEnum;
import org.example.util.DataFactory;
import org.example.util.DatabaseUtil;
import org.example.util.TestJwtTokenProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
public class PersonControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private MockMvc mockMvc;
    @Autowired
    private DatabaseUtil databaseUtil;

    @Autowired
    private TestJwtTokenProvider provider;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();
    }

    @Test
    public void testRegisterPerson() {
        PersonDto expected = DataFactory.getPersonDtoForTest(null);
        Assertions.assertDoesNotThrow(() -> mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expected)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn());

    }

    @Test
    public void testReadPerson() throws Exception {
        Person expected = databaseUtil.createPerson();
        MvcResult mvcResult = mockMvc.perform(get("/person/{id}", expected.getId())
                        .header("Authorization", "Bearer " + provider.buildJwtToken(RoleEnum.ROLE_USER))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        PersonDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PersonDto.class);
        Assert.assertEquals(actual.getLastName(), expected.getLastName());
    }

    @Test
    public void testReadPersonThrowEntityNotFoundException() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/person/{id}", 321L)
                        .header("Authorization", "Bearer " + provider.buildJwtToken(RoleEnum.ROLE_USER))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(404, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        Person person = databaseUtil.createPerson();
        PersonDto expected = DataFactory.getPersonDtoForTest(person.getId());

        MvcResult mvcResult = mockMvc.perform(put("/person")
                        .header("Authorization", "Bearer " + provider.buildJwtToken(RoleEnum.ROLE_USER))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expected)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertEquals(200, mvcResult.getResponse().getStatus());

        mvcResult = mockMvc.perform(get("/person/{id}", expected.getId())
                        .header("Authorization", "Bearer " + provider.buildJwtToken(RoleEnum.ROLE_USER))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        PersonDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PersonDto.class);

        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
    }


    @Test
    public void testDeletePerson() throws Exception {
        Long id = databaseUtil.createPerson().getId();
        MvcResult mvcResult = mockMvc.perform(delete("/person/{id}", id)
                        .header("Authorization", "Bearer " + provider.buildJwtToken(RoleEnum.ROLE_USER)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());

    }
}
