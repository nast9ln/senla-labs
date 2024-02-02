package ru.labs.coffer.controller;

import ru.labs.coffer.dto.security.JwtPerson;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testLoginEndpoint() throws Exception {
        String personDto = createPerson("loginForTest", "passw0rd");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personDto))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"login\": \"loginForTest\", \"password\": \"passw0rd\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists());
    }

    @Test
    public void testLoginEndpointWrongCredentials() throws Exception {
        String register = createPerson("loginForTestWrong", "passw0rd");
        String login = createPerson("loginForTestWrong", "wrongPassw0rd");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(register))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(login)
                )
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @SneakyThrows
    public String createPerson(String login, String password) {
        return mapper.writeValueAsString(
                JwtPerson.builder()
                        .login(login)
                        .password(password)
                        .build()
        );
    }
}
