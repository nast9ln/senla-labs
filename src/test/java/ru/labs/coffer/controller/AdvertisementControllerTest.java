package ru.labs.coffer.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.labs.coffer.dto.*;
import ru.labs.coffer.dto.search.AdvertisementSearchDto;
import ru.labs.coffer.dto.search.BaseSearchDto;
import ru.labs.coffer.entity.*;
import ru.labs.coffer.mapper.CategoryMapper;
import ru.labs.coffer.mapper.PersonMapper;
import ru.labs.coffer.repository.AdvertisementRepository;
import ru.labs.coffer.util.DataFactory;
import ru.labs.coffer.util.DatabaseUtil;
import ru.labs.coffer.util.TestJwtTokenProvider;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static ru.labs.coffer.common.GeneralConstant.AUTHORIZATION_HEADER;
import static ru.labs.coffer.util.DatabaseUtil.TEST_LOGIN;
import static ru.labs.coffer.util.SpecificationUtils.createPath;


@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdvertisementControllerTest extends BaseMock {
    @Autowired
    private DatabaseUtil databaseUtil;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private TestJwtTokenProvider provider;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private AdvertisementRepository advertisementRepository;

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
                        .content(mapper.writeValueAsString(expected)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        AdvertisementDto actual = mapper.readValue(mvcResult.getResponse().getContentAsString(), AdvertisementDto.class);
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
        AdvertisementDto actual = mapper.readValue(mvcResult.getResponse().getContentAsString(), AdvertisementDto.class);
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
                        .content(mapper.writeValueAsString(expected)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

        mvcResult = mockMvc.perform(get("/advertisement/{id}", expected.getId())
                        .header(AUTHORIZATION_HEADER, token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        AdvertisementDto actual = mapper.readValue(mvcResult.getResponse().getContentAsString(), AdvertisementDto.class);

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

    @Test
    public void testGetHistoryByPersonId() throws Exception {
        Person person = databaseUtil.createPerson("getHistoryTest");
        Advertisement advertisement1 = databaseUtil.createAdvertisement(person, "header0");
        Advertisement advertisement2 = databaseUtil.createAdvertisement(person, "header1");
        advertisement2.setIsDeleted(true);

        person.setAdvertisements(Set.of(advertisement1, advertisement2));

        String token = provider.buildJwtToken("getHistoryTest");
        mockAuth("getHistoryTest");

        MvcResult mvcResult = mockMvc.perform(get("/advertisement/history/{id}", person.getId())
                        .header(AUTHORIZATION_HEADER, token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
        Assertions.assertEquals(person.getAdvertisements().size(), advertisementRepository.findByPersonId(person.getId()).size());
    }

    @Test
    public void testAllOrderedByRatingAndCreatedDate() throws Exception {
        databaseUtil.clearAdvertisementTable();

        Person person1 = databaseUtil.createPerson("OrderedByRatingAndCreatedDate1", 5);
        databaseUtil.createAdvertisement(person1, "adv1");
        databaseUtil.createAdvertisement(person1, "adv2", new TopParam(Instant.now(), 1000000));

        Person person2 = databaseUtil.createPerson("OrderedByRatingAndCreatedDate2", 3);
        databaseUtil.createAdvertisement(person2, "adv3");
        databaseUtil.createAdvertisement(person1, "adv5");
        databaseUtil.createAdvertisement(person2, "adv4");

        String token = provider.buildJwtToken("OrderedByRatingAndCreatedDate1");
        mockAuth("OrderedByRatingAndCreatedDate1");
        BaseSearchDto searchDto = new BaseSearchDto();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setSort(List.of(
                SortInstruction.of(Sort.Direction.DESC, createPath(Advertisement_.person, Person_.rating)),
                SortInstruction.of(Sort.Direction.DESC, createPath(Advertisement_.createdDate))
        ));
        searchDto.setPageInfo(pageInfo);

        String response = mockMvc.perform(post("/advertisement/search")
                        .content(mapper.writeValueAsString(searchDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION_HEADER, token))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode rootNode = mapper.readTree(response);
        JsonNode contentNode = rootNode.get("content");

        List<AdvertisementDto> advs = mapper.readValue(contentNode.toString(), new TypeReference<List<AdvertisementDto>>() {
        });

        databaseUtil.clearAdvertisementTable();
        Assertions.assertEquals(5, advs.size());
        Assertions.assertEquals("adv5", advs.get(0).getHeader());
    }

    @Test
    public void testAllOrderedByTopAndCreatedDate() throws Exception {
        databaseUtil.clearAdvertisementTable();

        Person person1 = databaseUtil.createPerson("OrderedByTopAndCreatedDate1");
        databaseUtil.createAdvertisement(person1, "adv1");
        databaseUtil.createAdvertisement(person1, "adv2", new TopParam(Instant.now(), 1000000));

        Person person2 = databaseUtil.createPerson("OrderedByTopAndCreatedDate2");
        databaseUtil.createAdvertisement(person2, "adv3", new TopParam(Instant.now(), 1000000));
        databaseUtil.createAdvertisement(person2, "adv4");
        databaseUtil.createAdvertisement(person1, "adv5");

        String token = provider.buildJwtToken("OrderedByTopAndCreatedDate1");
        mockAuth("OrderedByTopAndCreatedDate1");
        BaseSearchDto searchDto = new BaseSearchDto();
        PageInfo pageInfo = new PageInfo();
        pageInfo.setSort(List.of(
                SortInstruction.of(Sort.Direction.DESC, createPath(Advertisement_.topParam, TopParam_.timeTopStart)),
                SortInstruction.of(Sort.Direction.DESC, createPath(Advertisement_.createdDate))
        ));
        searchDto.setPageInfo(pageInfo);

        String response = mockMvc.perform(post("/advertisement/search")
                        .content(mapper.writeValueAsString(searchDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION_HEADER, token))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode rootNode = mapper.readTree(response);
        JsonNode contentNode = rootNode.get("content");
        List<AdvertisementDto> advs = mapper.readValue(contentNode.toString(), new TypeReference<List<AdvertisementDto>>() {
        });

        databaseUtil.clearAdvertisementTable();
        Assertions.assertEquals(5, advs.size());
        Assertions.assertEquals("adv3", advs.get(0).getHeader());
    }

    @Test
    public void testAllOrderedByTopAndCreatedDateAndUsedCostFilter() throws Exception {
        databaseUtil.clearAdvertisementTable();

        Person person1 = databaseUtil.createPerson("testAllOrderedByTopAndCreatedDateAndUsedCostFilter1");
        databaseUtil.createAdvertisement(person1, "adv1", null, 100);
        databaseUtil.createAdvertisement(person1, "adv2", new TopParam(Instant.now(), 1000000), 150);

        Person person2 = databaseUtil.createPerson("testAllOrderedByTopAndCreatedDateAndUsedCostFilter2");
        databaseUtil.createAdvertisement(person2, "adv3", new TopParam(Instant.now(), 1000000), 50);
        databaseUtil.createAdvertisement(person2, "adv4", null, 600);
        databaseUtil.createAdvertisement(person1, "adv5", null, 30);

        String token = provider.buildJwtToken("testAllOrderedByTopAndCreatedDateAndUsedCostFilter1");
        mockAuth("testAllOrderedByTopAndCreatedDateAndUsedCostFilter1");
        AdvertisementSearchDto searchDto = new AdvertisementSearchDto();
        searchDto.setMinCost(100);
        searchDto.setMaxCost(600);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setSort(List.of(
                SortInstruction.of(Sort.Direction.DESC, createPath(Advertisement_.topParam, TopParam_.timeTopStart)),
                SortInstruction.of(Sort.Direction.DESC, createPath(Advertisement_.createdDate))
        ));
        searchDto.setPageInfo(pageInfo);

        String response = mockMvc.perform(post("/advertisement/search")
                        .content(mapper.writeValueAsString(searchDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(AUTHORIZATION_HEADER, token))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode rootNode = mapper.readTree(response);
        JsonNode contentNode = rootNode.get("content");
        List<AdvertisementDto> advs = mapper.readValue(contentNode.toString(), new TypeReference<List<AdvertisementDto>>() {
        });

        Assertions.assertEquals(3, advs.size());
        Assertions.assertEquals("adv2", advs.get(0).getHeader());
        Assertions.assertEquals("adv4", advs.get(1).getHeader());
        Assertions.assertEquals("adv1", advs.get(2).getHeader());

    }
}