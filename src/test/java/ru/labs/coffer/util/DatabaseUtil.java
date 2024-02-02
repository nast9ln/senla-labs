package ru.labs.coffer.util;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.labs.coffer.entity.*;
import ru.labs.coffer.enums.CategoryType;
import ru.labs.coffer.enums.Gender;
import ru.labs.coffer.enums.RoleEnum;
import ru.labs.coffer.repository.*;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Component
@Transactional
@RequiredArgsConstructor
public class DatabaseUtil {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final AdvertisementRepository advertisementRepository;
    private final CategoryRepository categoryRepository;
    private final TopParamRepository topParamRepository;
    private final MessageRepository messageRepository;
    private final EntityManager entityManager;
    public static final String TEST_LOGIN = "for_test";
    public static final String TEST_HEADER = "red furTest";

    public Person createPerson() {
        Optional<Person> person = personRepository.findByLogin(TEST_LOGIN);
        if (person.isPresent()) {
            return person.get();
        }
        Person build = Person.builder()
                .gender(Gender.WOMAN)
                .login(TEST_LOGIN)
                .firstName("test1")
                .lastName("test1")
                .birthday(Instant.ofEpochSecond(1069965734))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("$2a$04$Xu2jFXFlnwgUma/Tzqgds.gLK72sIlvPHvOTsKkokTc44aq1ry8FC")
                .roles(Set.of(roleRepository.findByName(RoleEnum.ROLE_USER)))
                .totalRatings(0)
                .rating(0)
                .build();
        return personRepository.save(build);
    }

    public Person createPerson(String login) {
        Optional<Person> person = personRepository.findByLogin(login);
        if (person.isPresent()) {
            return person.get();
        }
        Person build = Person.builder()
                .gender(Gender.WOMAN)
                .login(login)
                .firstName("test1")
                .lastName("test1")
                .birthday(Instant.ofEpochSecond(1069965734))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("$2a$04$Xu2jFXFlnwgUma/Tzqgds.gLK72sIlvPHvOTsKkokTc44aq1ry8FC")
                .roles(Set.of(roleRepository.findByName(RoleEnum.ROLE_USER)))
                .totalRatings(0)
                .rating(0)
                .build();
        return personRepository.save(build);
    }

    public Person createPerson(String login, Integer rating) {
        Optional<Person> person = personRepository.findByLogin(login);
        if (person.isPresent()) {
            return person.get();
        }
        Person build = Person.builder()
                .gender(Gender.WOMAN)
                .login(login)
                .firstName("test1")
                .lastName("test1")
                .birthday(Instant.ofEpochSecond(1069965734))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("$2a$04$Xu2jFXFlnwgUma/Tzqgds.gLK72sIlvPHvOTsKkokTc44aq1ry8FC")
                .roles(Set.of(roleRepository.findByName(RoleEnum.ROLE_USER)))
                .totalRatings(1)
                .rating(rating)
                .build();
        return personRepository.save(build);
    }

    public Advertisement createAdvertisement() {
        Person person = createPerson();
        Optional<Advertisement> adv = advertisementRepository.findByHeaderAndPersonId(TEST_HEADER, person.getId());
        if (adv.isPresent()) {
            return adv.get();
        }
        Advertisement advertisement = Advertisement.builder()
                .person(person)
                .category(null)
                .topParam(null)
                .createdDate(Instant.now())
                .cost(111)
                .city("MinskTest")
                .header(TEST_HEADER)
                .description("new red furTest")
                .status("ACTIVETest")
                .mainImage(null)
                .isDeleted(false)
                .build();
        return advertisementRepository.save(advertisement);
    }

    public Advertisement createAdvertisement(Person person) {
        Optional<Advertisement> adv = advertisementRepository.findByHeaderAndPersonId(TEST_HEADER, person.getId());
        if (adv.isPresent()) {
            return adv.get();
        }
        Advertisement advertisement = Advertisement.builder()
                .person(person)
                .category(null)
                .topParam(null)
                .cost(111)
                .city("MinskTest")
                .header(TEST_HEADER)
                .description("new red furTest")
                .status("ACTIVETest")
                .mainImage(null)
                .isDeleted(false)
                .build();
        return advertisementRepository.save(advertisement);
    }

    public Advertisement createAdvertisement(Person person, String header) {
        Optional<Advertisement> adv = advertisementRepository.findByHeaderAndPersonId(header, person.getId());
        if (adv.isPresent()) {
            return adv.get();
        }
        Advertisement advertisement = Advertisement.builder()
                .person(person)
                .category(null)
                .topParam(null)
                .cost(111)
                .city("MinskTest")
                .header(header)
                .description("new red furTest")
                .status("ACTIVETest")
                .mainImage(null)
                .isDeleted(false)
                .build();
        return advertisementRepository.save(advertisement);
    }

    public Advertisement createAdvertisement(Person person, String header, TopParam topParam) {
        Optional<Advertisement> adv = advertisementRepository.findByHeaderAndPersonId(header, person.getId());
        if (adv.isPresent()) {
            return adv.get();
        }
        Advertisement advertisement = Advertisement.builder()
                .person(person)
                .category(null)
                .topParam(topParam)
                .createdDate(Instant.now())
                .cost(111)
                .city("MinskTest")
                .header(header)
                .description("new red furTest")
                .status("ACTIVETest")
                .mainImage(null)
                .isDeleted(false)
                .build();
        return advertisementRepository.save(advertisement);
    }

    public Advertisement createAdvertisement(Person person, String header, TopParam topParam, Integer cost) {
        Optional<Advertisement> adv = advertisementRepository.findByHeaderAndPersonId(header, person.getId());
        if (adv.isPresent()) {
            return adv.get();
        }
        Advertisement advertisement = Advertisement.builder()
                .person(person)
                .category(null)
                .topParam(topParam)
                .createdDate(Instant.now())
                .cost(cost)
                .city("MinskTest")
                .header(header)
                .description("new red furTest")
                .status("ACTIVETest")
                .mainImage(null)
                .isDeleted(false)
                .build();
        return advertisementRepository.save(advertisement);
    }

    public Category createCategory() {
        Category build = Category.builder()
                .name(CategoryType.CLOTHING.name())
                .build();
        return categoryRepository.save(build);
    }

    public TopParam createTopParam(Instant timeTopStart, Integer timeInTop) {
        TopParam topParam = TopParam.builder()
                .timeTopStart(timeTopStart)
                .timeInTop(timeInTop)
                .build();
        return topParamRepository.save(topParam);
    }

    public Message createMessage(String senderLogin, String recipientLogin, String advHeader) {
        Person sender = createPerson(senderLogin);
        Person recipient = createPerson(recipientLogin);
        Message message = Message.builder()
                .advertisement(createAdvertisement(recipient, advHeader))
                .sender(sender)
                .recipient(recipient)
                .text("test message" + Math.random())
                .build();
        return messageRepository.save(message);
    }

    @Transactional
    public void clearAdvertisementTable() {
        entityManager.createNativeQuery("DELETE FROM Comment").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM Image").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM Message").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM advertisement").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM top_param").executeUpdate();
    }
}
