package com.example.demo.controller;

import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
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

import static com.example.demo.util.DatabaseUtil.TEST_LOGIN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonControllerTest extends BaseController {
    @Autowired
    private TestJwtTokenProvider provider;

    @MockBean
    private JwtService jwtService;
    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    @Autowired
    private DatabaseUtil databaseUtil;

    @Test
    public void testRegisterPerson() {
        PersonDto expected = DataFactory.getPersonDtoForTest("register_login", null);

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

        when(jwtService.extractLogin(any())).thenReturn(TEST_LOGIN);
        when(jwtService.isTokenValid(any(), any())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(get("/person/{id}", expected.getId())
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        PersonDto actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PersonDto.class);
        Assertions.assertEquals(actual.getLastName(), expected.getLastName());
    }

//    @Test
//    public void testReadPersonThrowEntityNotFoundException() throws Exception {
//        String token = provider.buildJwtToken(TEST_LOGIN);
//        when(jwtService.extractLogin(any())).thenReturn(TEST_LOGIN);
//        when(jwtService.isTokenValid(any(), any())).thenReturn(true);
//
//        MvcResult mvcResult = mockMvc.perform(get("/person/{id}", 321L)
//                        .header("Authorization", token)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
////                .andExpect(status().isInternalServerError())
////                .andExpect(ServletException)
////                .andExpect(jsonPath("$.message").value("adv"))
//                .andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        System.out.println(status);
//    }

    @Test
    public void testUpdatePerson() throws Exception {

        Person person = databaseUtil.createPerson();
        PersonDto expected = DataFactory.getPersonDtoForTest(TEST_LOGIN, person.getId());

        String token = provider.buildJwtToken(TEST_LOGIN);
        when(jwtService.extractLogin(any())).thenReturn(TEST_LOGIN);
        when(jwtService.isTokenValid(any(), any())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(put("/person")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expected)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

        mvcResult = mockMvc.perform(get("/person/{id}", expected.getId())
                        .header("Authorization", token)
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

        when(jwtService.extractLogin(any())).thenReturn(TEST_LOGIN);
        when(jwtService.isTokenValid(any(), any())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(delete("/person/{id}", id)
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

    }
}
