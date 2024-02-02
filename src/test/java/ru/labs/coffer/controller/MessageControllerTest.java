package ru.labs.coffer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.MessageDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.dto.search.MessageSearchDto;
import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.Message;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.mapper.AdvertisementMapper;
import ru.labs.coffer.mapper.PersonMapper;
import ru.labs.coffer.repository.MessageRepository;
import ru.labs.coffer.util.DataFactory;
import ru.labs.coffer.util.DatabaseUtil;
import ru.labs.coffer.util.TestJwtTokenProvider;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.labs.coffer.common.GeneralConstant.AUTHORIZATION_HEADER;
import static ru.labs.coffer.util.DatabaseUtil.TEST_LOGIN;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageControllerTest extends BaseMock {
    @Autowired
    private DatabaseUtil databaseUtil;
    @Autowired
    private TestJwtTokenProvider provider;
    @Autowired
    protected PersonMapper personMapper;
    @Autowired
    protected AdvertisementMapper advertisementMapper;

    @Test
    public void sendMessage() throws Exception {
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
    }

    /**
     * Получение диалога НЕ создателем объявления.
     *
     * @throws Exception
     */
    @Test
    public void getDialogBySender() throws Exception {
        String header = "get-dialog-advertisement";
        String sender = "sender_for_message";
        String recipient = "recipient_for_message";
        databaseUtil.createMessage(sender, recipient, header);
        databaseUtil.createMessage(sender, recipient, header);
        databaseUtil.createMessage(sender, recipient, header);
        Message message = databaseUtil.createMessage(sender, recipient, header);
        MessageSearchDto searchDto = new MessageSearchDto();
        searchDto.setAdvertisementId(message.getAdvertisement().getId());

        String token = provider.buildJwtToken(sender);
        mockAuth(sender);

        String response = mockMvc.perform(post("/message/dialog")
                        .header(AUTHORIZATION_HEADER, token)
                        .content(mapper.writeValueAsString(searchDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode rootNode = mapper.readTree(response);
        JsonNode contentNode = rootNode.get("content");

        List<MessageDto> result = mapper.readValue(contentNode.toString(), new TypeReference<List<MessageDto>>() {
        });

        Assertions.assertEquals(4, result.size());
    }

    @Test
    public void getPersonsForDialogByCreator() throws Exception {
        String header = "get-dialog-advertisement-persons-by-creator";
        String recipient = "recipient_for_message_by_creator";
        databaseUtil.createMessage("sender_for_message1", recipient, header);
        databaseUtil.createMessage("sender_for_message2", recipient, header);
        databaseUtil.createMessage("sender_for_message3", recipient, header);
        databaseUtil.createMessage("sender_for_message4", recipient, header);

        String token = provider.buildJwtToken(recipient);

        mockAuth(recipient);

        String response = mockMvc.perform(get("/message/creator")
                        .header(AUTHORIZATION_HEADER, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode rootNode = mapper.readTree(response);
        JsonNode contentNode = rootNode.get("content");
        List<PersonDto> result = mapper.readValue(contentNode.toString(), new TypeReference<List<PersonDto>>() {
        });

        Assertions.assertEquals(4, result.size());
    }

    @Test
    public void getDialogByCreator() throws Exception {
        String header = "get-dialog-advertisement-by-creator";
        String recipient = "recipient_for_dialog_message2";
        String sender = "sender_for_message1";
        databaseUtil.createMessage(sender, recipient, header);
        databaseUtil.createMessage(recipient, sender, header);
        databaseUtil.createMessage(sender, recipient, header);
        databaseUtil.createMessage(sender, recipient, header);
        Message message = databaseUtil.createMessage(sender, recipient, header);

        String token = provider.buildJwtToken(sender);
        mockAuth(sender);

        String response = mockMvc.perform(get("/message/by-creator/{advertisementId}/{personId}", message.getAdvertisement().getId(), message.getRecipient().getId())
                        .header(AUTHORIZATION_HEADER, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode rootNode = mapper.readTree(response);
        JsonNode contentNode = rootNode.get("content");
        List<MessageDto> result = mapper.readValue(contentNode.toString(), new TypeReference<List<MessageDto>>() {
        });

        Assertions.assertEquals(4, result.size());
    }

}