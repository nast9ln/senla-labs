package ru.labs.coffer.util;

import ru.labs.coffer.dto.AdvertisementDto;
import ru.labs.coffer.dto.CategoryDto;
import ru.labs.coffer.dto.PersonDto;
import ru.labs.coffer.dto.RoleDto;
import ru.labs.coffer.entity.Advertisement;
import ru.labs.coffer.entity.Person;
import ru.labs.coffer.entity.Role;
import ru.labs.coffer.enums.CategoryType;
import ru.labs.coffer.enums.Gender;
import ru.labs.coffer.enums.RoleEnum;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.util.Set;

@UtilityClass
public class DataFactory {
    public static final String TEST_LOGIN = "for_test";

    public Person getPersonForTest(Long id) {
        return Person.builder()
                .id(id)
                .gender(Gender.WOMAN)
                .login(TEST_LOGIN)
                .firstName("test1")
                .lastName("test1")
                .birthday(Instant.ofEpochSecond(1069965734))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new Role(RoleEnum.ROLE_USER)))
                .build();
    }

    public PersonDto getPersonDtoForTest(String login, Long id) {
        return PersonDto.builder()
                .id(id)
                .gender(Gender.WOMAN)
                .login(login)
                .firstName("testDto")
                .lastName("testDto")
                .birthday(Instant.ofEpochSecond(1069965734))
                .city("VitebskDto")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202Dto")
                .roles(Set.of(new RoleDto(RoleEnum.ROLE_USER)))
                .build();
    }

    public Advertisement getAdvertisementForTest(Long id) {
        Person person1 = getPersonForTest(null);
        return Advertisement.builder()
                .id(id)
                .person(person1)
                .category(null)
                .topParam(null)
                .createdDate(Instant.now())
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImage(null)
                .build();
    }

    public AdvertisementDto getAdvertisementDtoForTest(Long id) {
        PersonDto person1 = getPersonDtoForTest("for_adv", null);
        CategoryDto category = getCategoryForTest(null);

        return AdvertisementDto.builder()
                .id(id)
                .person(person1)
                .category(category)
                .topParam(null)
                .createdDate(Instant.now())
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImage(null)
                .build();
    }

    public CategoryDto getCategoryForTest(Long id) {
        return CategoryDto.builder()
                .id(id)
                .name(CategoryType.CLOTHING.name())
                .build();
    }
}
