package org.example.repository;

import junit.framework.TestCase;
import org.example.config.TestConnectionConfig;
import org.example.entity.Advertisement;
import org.example.entity.Person;
import org.example.entity.Role;
import org.example.enums.Gender;
import org.example.enums.RoleEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {TestConnectionConfig.class})
@WebAppConfiguration
public class AdvertisementRepositoryTest extends TestCase {
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Transactional
    public void testReadByPersonId() {
        Person person1 = personRepository.save(Person.builder()
                .gender(Gender.WOMAN)
                .firstName("test1")
                .lastName("test1")
                .birthday(LocalDate.of(2005, 1, 14).atStartOfDay().toInstant(ZoneOffset.UTC))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new Role(RoleEnum.USER)))
                .build());

        Person person2 = personRepository.save(Person.builder()
                .gender(Gender.WOMAN)
                .firstName("test2")
                .lastName("test2")
                .birthday(LocalDate.of(2005, 1, 14).atStartOfDay().toInstant(ZoneOffset.UTC))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new Role(RoleEnum.USER)))
                .build());

        advertisementRepository.save(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(LocalDateTime.now().toInstant(ZoneOffset.UTC))
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());

        advertisementRepository.save(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(LocalDateTime.now().toInstant(ZoneOffset.UTC))
                .cost(200)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());

        Assert.assertEquals(2, advertisementRepository.findByPersonId(person1.getId()).size());
        Assert.assertEquals(0, advertisementRepository.findByPersonId(person2.getId()).size());
    }

}