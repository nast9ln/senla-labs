package org.example.repository;

import junit.framework.TestCase;
import org.example.config.TestConnectionConfig;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {TestConnectionConfig.class})
@WebAppConfiguration

public class PersonRepositoryTest extends TestCase {
    @Autowired
    private PersonRepository personRepository;

    @PersistenceContext
    protected EntityManager entityManager;


    @Test
    public void testFindByPersonName() {
        Person person = personRepository.save(Person.builder()
                .gender(Gender.WOMAN)
                .firstName("test")
                .lastName("test")
                .birthday(Instant.ofEpochSecond(1069965734))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new Role(RoleEnum.USER)))
                .build());

        Person dbPerson = personRepository.findByFirstNameAndLastName("test", "test").get();
        Assert.assertEquals(person.getFirstName(), dbPerson.getFirstName());
        Assert.assertEquals(person.getLastName(), dbPerson.getLastName());
        Assert.assertEquals(person.getBirthday(), dbPerson.getBirthday());
        Assert.assertEquals(person.getCity(), dbPerson.getCity());
        Assert.assertEquals(person.getPhone(), dbPerson.getPhone());
        Assert.assertEquals(person.getEmail(), dbPerson.getEmail());
        Assert.assertEquals(person.getPassword(), dbPerson.getPassword());
        Assert.assertEquals(person.isDeleted(), dbPerson.isDeleted());
        Assert.assertNotNull(dbPerson.getRoles());
    }
}