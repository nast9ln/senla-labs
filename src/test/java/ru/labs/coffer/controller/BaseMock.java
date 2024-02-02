package ru.labs.coffer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import ru.labs.coffer.mapper.AdvertisementMapper;
import ru.labs.coffer.mapper.CategoryMapper;
import ru.labs.coffer.mapper.PersonMapper;
import ru.labs.coffer.repository.AdvertisementRepository;
import ru.labs.coffer.repository.CategoryRepository;
import ru.labs.coffer.repository.PersonRepository;
import ru.labs.coffer.service.security.JwtService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static ru.labs.coffer.util.DatabaseUtil.TEST_LOGIN;


@SpringBootTest
@AutoConfigureMockMvc
public class BaseMock {
    @Autowired
    protected MockMvc mockMvc;
    protected ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
    @MockBean
    private JwtService jwtService;


    public void mockAuth(String login) {
        when(jwtService.extractLogin(any())).thenReturn(login);
        when(jwtService.isTokenValid(any(), any())).thenReturn(true);
    }

}
