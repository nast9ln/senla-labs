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
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {TestConnectionConfig.class},
        loader = AnnotationConfigContextLoader.class)
public class AdvertisementRepositoryTest extends TestCase {
    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testDeleteByPersonId() {

        Person person1 = personRepository.create(Person.builder()
                .gender(Gender.WOMAN)
                .firstName("test1")
                .lastName("test1")
                .birthday(LocalDate.of(2005, 1, 14))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new Role(RoleEnum.USER)))
                .build());

        advertisementRepository.create(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(LocalDateTime.now())
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());


        advertisementRepository.deleteByPersonId(person1.getId());
        Assert.assertEquals(Optional.empty(), advertisementRepository.get(person1.getId()));
    }

    @Test
    @Transactional
    public void testReadByPersonId() {
        Person person1 = personRepository.create(Person.builder()
                .gender(Gender.WOMAN)
                .firstName("test1")
                .lastName("test1")
                .birthday(LocalDate.of(2005, 1, 14))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new Role(RoleEnum.USER)))
                .build());

        Person person2 = personRepository.create(Person.builder()
                .gender(Gender.WOMAN)
                .firstName("test2")
                .lastName("test2")
                .birthday(LocalDate.of(2005, 1, 14))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new Role(RoleEnum.USER)))
                .build());

        advertisementRepository.create(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(LocalDateTime.now())
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());

        advertisementRepository.create(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(LocalDateTime.now())
                .cost(200)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());

        Assert.assertEquals(2, advertisementRepository.readByPersonId(person1.getId()).size());
        Assert.assertEquals(0, advertisementRepository.readByPersonId(person2.getId()).size());
    }

    @Test
    public void testFindAdvertisements() {

        Person person1 = personRepository.create(Person.builder()
                .gender(Gender.WOMAN)
                .firstName("test1")
                .lastName("test1")
                .birthday(LocalDate.of(2005, 1, 14))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new Role(RoleEnum.USER)))
                .build());

        Person person2 = personRepository.create(Person.builder()
                .gender(Gender.WOMAN)
                .firstName("test2")
                .lastName("test2")
                .birthday(LocalDate.of(2005, 1, 14))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new Role(RoleEnum.USER)))
                .build());

        advertisementRepository.create(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(LocalDateTime.now())
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());

        advertisementRepository.create(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .cost(200)
                .createdDate(LocalDateTime.now())
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());
        List<Advertisement> advertisements = advertisementRepository.findAdvertisements(0, 10);
        Assert.assertEquals(10, advertisements.size());
    }

    @Test
    public void testFindAdvertisementsWithEntityGraph() {
        Person person1 = personRepository.create(Person.builder()
                .gender(Gender.WOMAN)
                .firstName("test1")
                .lastName("test1")
                .birthday(LocalDate.of(2005, 1, 14))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new Role(RoleEnum.USER)))
                .build());

        Person person2 = personRepository.create(Person.builder()
                .gender(Gender.WOMAN)
                .firstName("test2")
                .lastName("test2")
                .birthday(LocalDate.of(2005, 1, 14))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new Role(RoleEnum.USER)))
                .build());

        advertisementRepository.create(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(LocalDateTime.now())
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());

        advertisementRepository.create(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .cost(200)
                .createdDate(LocalDateTime.now())
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());
        List<Advertisement> advertisements = advertisementRepository.findAdvertisementsWithEntityGraph();
        Assert.assertEquals(advertisementRepository.getAll().size(), advertisements.size());
    }
    }