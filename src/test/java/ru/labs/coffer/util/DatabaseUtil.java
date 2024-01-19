package ru.labs.coffer.util;

import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.Category;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.enums.CategoryType;
import ru.labs.coffer.enums.Gender;
import ru.labs.coffer.enums.RoleEnum;
import ru.labs.coffer.repository.AdvertisementRepository;
import ru.labs.coffer.repository.CategoryRepository;
import ru.labs.coffer.repository.PersonRepository;
import ru.labs.coffer.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Set;

@Component
@Transactional
@RequiredArgsConstructor
public class DatabaseUtil {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final AdvertisementRepository advertisementRepository;
    private final CategoryRepository categoryRepository;

    public static final String TEST_LOGIN = "for_test";

    public Person createPerson() {
        if (personRepository.existsByLogin(TEST_LOGIN)) {
            return personRepository.findByLogin(TEST_LOGIN).get();
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
                .build();
        return personRepository.save(build);
    }

    public Advertisement createAdvertisement() {
        Person person = createPerson();
        Advertisement advertisement = Advertisement.builder()
                .person(person)
                .category(null)
                .topParam(null)
                .createdDate(Instant.now())
                .cost(111)
                .city("MinskTest")
                .header("red furTest")
                .description("new red furTest")
                .status("ACTIVETest")
                .mainImage(null)
                .build();
        return advertisementRepository.save(advertisement);
    }

    public Category createCategory() {
        Category build = Category.builder()
                .name(CategoryType.CLOTHING.name())
                .build();
        return categoryRepository.save(build);
    }
}
