package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.example.util.TestUtils.getLocalDateTime;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {TestConnectionConfig.class},
        loader = AnnotationConfigContextLoader.class)
public class AbstractDaoImplTest {
    @Autowired
    private AdvertisementRepository advertisementRepository;
    @Autowired
    private PersonRepository personRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    @Test
    public void testGetAll() {
        entityManager.createQuery("DELETE FROM Comment c WHERE c.advertisement.id IN (SELECT a.id FROM Advertisement a)").executeUpdate();
        entityManager.createQuery("DELETE FROM Image i WHERE i.advertisement.id IN (SELECT a.id FROM Advertisement a)").executeUpdate();
        entityManager.createQuery("DELETE FROM Message m WHERE m.advertisement.id IN (SELECT a.id FROM Advertisement a)").executeUpdate();

        entityManager.createQuery("DELETE FROM Advertisement a").executeUpdate();
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
        Assert.assertEquals(2, advertisementRepository.getAll().size());
    }

    @Test
    public void testGet() {
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
        LocalDateTime createdDateTime = getLocalDateTime();

        Advertisement advertisement = advertisementRepository.create(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(createdDateTime)
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());
        Advertisement dbAdvertisement = advertisementRepository.get(advertisement.getId()).orElse(null);
        assert dbAdvertisement != null;
        Assert.assertEquals(advertisement.getId(), dbAdvertisement.getId());
        Assert.assertEquals(advertisement.getCategoryId(), dbAdvertisement.getCategoryId());
        Assert.assertNotNull(dbAdvertisement.getPerson());
        Assert.assertEquals(advertisement.getTopParamId(), dbAdvertisement.getTopParamId());
        Assert.assertEquals(advertisement.getCreatedDate(), dbAdvertisement.getCreatedDate());
        Assert.assertEquals(advertisement.getCost(), dbAdvertisement.getCost());
        Assert.assertEquals(advertisement.getCity(), dbAdvertisement.getCity());
        Assert.assertEquals(advertisement.getHeader(), dbAdvertisement.getHeader());
        Assert.assertEquals(advertisement.getDescription(), dbAdvertisement.getDescription());
        Assert.assertEquals(advertisement.getStatus(), dbAdvertisement.getStatus());
        Assert.assertEquals(advertisement.getMainImageId(), dbAdvertisement.getMainImageId());
        Assert.assertEquals(advertisement.isDeleted(), dbAdvertisement.isDeleted());
    }

    @Test
    public void testUpdate() {
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
        LocalDateTime createdDateTime = getLocalDateTime();

        Advertisement advertisement = advertisementRepository.create(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(createdDateTime)
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());

        advertisement.setCity("Moscow");
        advertisementRepository.update(advertisement);

        Advertisement dbAdvertisement = advertisementRepository.get(advertisement.getId()).orElse(null);
        assert dbAdvertisement != null;
        Assert.assertEquals(advertisement.getId(), dbAdvertisement.getId());
        Assert.assertEquals(advertisement.getCategoryId(), dbAdvertisement.getCategoryId());
        Assert.assertNotNull(dbAdvertisement.getPerson());
        Assert.assertEquals(advertisement.getTopParamId(), dbAdvertisement.getTopParamId());
        Assert.assertEquals(advertisement.getCreatedDate(), dbAdvertisement.getCreatedDate());
        Assert.assertEquals(advertisement.getCost(), dbAdvertisement.getCost());
        Assert.assertEquals(advertisement.getCity(), dbAdvertisement.getCity());
        Assert.assertEquals(advertisement.getHeader(), dbAdvertisement.getHeader());
        Assert.assertEquals(advertisement.getDescription(), dbAdvertisement.getDescription());
        Assert.assertEquals(advertisement.getStatus(), dbAdvertisement.getStatus());
        Assert.assertEquals(advertisement.getMainImageId(), dbAdvertisement.getMainImageId());
        Assert.assertEquals(advertisement.isDeleted(), dbAdvertisement.isDeleted());

    }

    @Test
    public void testDelete() {
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
        LocalDateTime createdDateTime = getLocalDateTime();

        Advertisement advertisement = advertisementRepository.create(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(createdDateTime)
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());

        advertisementRepository.delete(advertisement.getId());
        advertisement = advertisementRepository.get(advertisement.getId()).orElse(null);
        assert advertisement != null;
        Assert.assertTrue(advertisement.isDeleted());
    }


    @Test
    @DependsOn("liquibase")
    public void testCreate() {
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
        LocalDateTime createdDateTime = getLocalDateTime();

        Advertisement advertisement = advertisementRepository.create(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(createdDateTime)
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());

        Advertisement dbAdvertisement = advertisementRepository.get(advertisement.getId()).orElse(null);
        assert dbAdvertisement != null;
        Assert.assertEquals(advertisement.getId(), dbAdvertisement.getId());
        Assert.assertEquals(advertisement.getCategoryId(), dbAdvertisement.getCategoryId());
        Assert.assertNotNull(dbAdvertisement.getPerson());
        Assert.assertEquals(advertisement.getTopParamId(), dbAdvertisement.getTopParamId());
        Assert.assertEquals(advertisement.getCreatedDate(), dbAdvertisement.getCreatedDate());
        Assert.assertEquals(advertisement.getCost(), dbAdvertisement.getCost());
        Assert.assertEquals(advertisement.getCity(), dbAdvertisement.getCity());
        Assert.assertEquals(advertisement.getHeader(), dbAdvertisement.getHeader());
        Assert.assertEquals(advertisement.getDescription(), dbAdvertisement.getDescription());
        Assert.assertEquals(advertisement.getStatus(), dbAdvertisement.getStatus());
        Assert.assertEquals(advertisement.getMainImageId(), dbAdvertisement.getMainImageId());
        Assert.assertEquals(advertisement.isDeleted(), dbAdvertisement.isDeleted());

    }


    @Test
    public void testTestDeleteAll() {
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
        LocalDateTime createdDateTime = getLocalDateTime();

        Advertisement advertisement = advertisementRepository.create(Advertisement.builder()
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(createdDateTime)
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .isDeleted(false)
                .build());
        advertisementRepository.deleteAll();

        Set<Advertisement> allAdvertisements = advertisementRepository.getAll();
        for (Advertisement ad : allAdvertisements) {
            Assert.assertTrue(ad.isDeleted());
        }
    }


}