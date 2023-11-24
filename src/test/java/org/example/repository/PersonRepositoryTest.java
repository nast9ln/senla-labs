//package org.example.repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import org.example.config.TestConnectionConfig;
//import org.example.entity.Person;
//import org.example.entity.Role;
//import org.example.enums.Gender;
//import org.example.enums.RoleEnum;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.Set;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//        classes = {TestConnectionConfig.class},
//        loader = AnnotationConfigContextLoader.class)
//public class PersonRepositoryTest {
//    @Autowired
//    private PersonRepository personRepository;
//
//    @PersistenceContext
//    protected EntityManager entityManager;
//
//
//    @Test
//    public void testFindByPersonName() {
//        Person person = personRepository.create(Person.builder()
//                .gender(Gender.WOMAN)
//                .firstName("test")
//                .lastName("test")
//                .birthday(LocalDate.of(2005, 1, 14))
//                .city("Vitebsk")
//                .phone("+211")
//                .email("nast9ln@h.com")
//                .password("1202")
//                .isDeleted(false)
//                .roles(Set.of(new Role(RoleEnum.USER)))
//                .build());
//
//        Person dbPerson = personRepository.findByPersonName("test", "test").get(0);
//        Assert.assertEquals(person.getFirstName(), dbPerson.getFirstName());
//        Assert.assertEquals(person.getLastName(), dbPerson.getLastName());
//        Assert.assertEquals(person.getBirthday(), dbPerson.getBirthday());
//        Assert.assertEquals(person.getCity(), dbPerson.getCity());
//        Assert.assertEquals(person.getPhone(), dbPerson.getPhone());
//        Assert.assertEquals(person.getEmail(), dbPerson.getEmail());
//        Assert.assertEquals(person.getPassword(), dbPerson.getPassword());
//        Assert.assertEquals(person.isDeleted(), dbPerson.isDeleted());
//        Assert.assertNotNull(dbPerson.getRoles());
//    }
//
//
//    @Test
//    @Transactional
//    public void testFindAllWithEntityGraph() {
//        entityManager.createQuery("DELETE FROM Comment c WHERE c.advertisement.id IN (SELECT a.id FROM Advertisement a)").executeUpdate();
//        entityManager.createQuery("DELETE FROM Image i WHERE i.advertisement.id IN (SELECT a.id FROM Advertisement a)").executeUpdate();
//        entityManager.createQuery("DELETE FROM Message m WHERE m.advertisement.id IN (SELECT a.id FROM Advertisement a)").executeUpdate();
//        entityManager.createQuery("DELETE FROM Advertisement a").executeUpdate();
//        entityManager.createQuery("DELETE FROM Person p").executeUpdate();
//
//        Person person1 = personRepository.create(Person.builder()
//                .gender(Gender.WOMAN)
//                .firstName("test1")
//                .lastName("test1")
//                .birthday(LocalDate.of(2005, 1, 14))
//                .city("Vitebsk")
//                .phone("+211")
//                .email("nast9ln@h.com")
//                .password("1202")
//                .isDeleted(false)
//                .roles(Set.of(new Role(RoleEnum.USER)))
//                .build());
//
//        Person person2 = personRepository.create(Person.builder()
//                .gender(Gender.WOMAN)
//                .firstName("test2")
//                .lastName("test2")
//                .birthday(LocalDate.of(2005, 1, 14))
//                .city("Vitebsk")
//                .phone("+211")
//                .email("nast9ln@h.com")
//                .password("1202")
//                .isDeleted(false)
//                .roles(Set.of(new Role(RoleEnum.USER)))
//                .build());
//
//        Set<Person> persons = personRepository.findAllWithEntityGraph();
//
//        Assert.assertEquals(2, persons.size());
//        Assert.assertTrue(persons.contains(person1));
//        Assert.assertTrue(persons.contains(person2));
//    }
//
//    @Test
//    @Transactional
//    public void testTestFindAllWithJPQL() {
//        entityManager.createQuery("DELETE FROM Comment c WHERE c.advertisement.id IN (SELECT a.id FROM Advertisement a)").executeUpdate();
//        entityManager.createQuery("DELETE FROM Image i WHERE i.advertisement.id IN (SELECT a.id FROM Advertisement a)").executeUpdate();
//        entityManager.createQuery("DELETE FROM Message m WHERE m.advertisement.id IN (SELECT a.id FROM Advertisement a)").executeUpdate();
//        entityManager.createQuery("DELETE FROM Advertisement a").executeUpdate();
//        entityManager.createQuery("DELETE FROM Person p").executeUpdate();
//
//        Person person1 = personRepository.create(Person.builder()
//                .gender(Gender.WOMAN)
//                .firstName("test1")
//                .lastName("test1")
//                .birthday(LocalDate.of(2005, 1, 14))
//                .city("Vitebsk")
//                .phone("+211")
//                .email("nast9ln@h.com")
//                .password("1202")
//                .isDeleted(false)
//                .roles(Set.of(new Role(RoleEnum.USER)))
//                .build());
//
//        Person person2 = personRepository.create(Person.builder()
//                .gender(Gender.WOMAN)
//                .firstName("test2")
//                .lastName("test2")
//                .birthday(LocalDate.of(2005, 1, 14))
//                .city("Vitebsk")
//                .phone("+211")
//                .email("nast9ln@h.com")
//                .password("1202")
//                .isDeleted(false)
//                .roles(Set.of(new Role(RoleEnum.USER)))
//                .build());
//
//        Set<Person> persons = personRepository.findAllWithJPQL();
//
//        Assert.assertEquals(2, persons.size());
//        Assert.assertTrue(persons.contains(person1));
//        Assert.assertTrue(persons.contains(person2));
//    }
//
//}