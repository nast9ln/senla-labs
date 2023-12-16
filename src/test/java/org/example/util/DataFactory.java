package org.example.util;

import lombok.experimental.UtilityClass;
import org.example.dto.AdvertisementDto;
import org.example.dto.PersonDto;
import org.example.dto.RoleDto;
import org.example.entity.Advertisement;
import org.example.entity.Person;
import org.example.entity.Role;
import org.example.enums.Gender;
import org.example.enums.RoleEnum;

import java.time.Instant;
import java.util.Set;

@UtilityClass
public class DataFactory {

    public Person getPersonForTest(Long id) {
        return Person.builder()
                .id(id)
                .gender(Gender.WOMAN)
                .login("test" + Math.random())
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

    public PersonDto getPersonDtoForTest(Long id) {
        return PersonDto.builder()
                .id(id)
                .gender(Gender.WOMAN)
                .login("testDto" + Math.random())
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
                .categoryId(null)
                .topParamId(null)
                .createdDate(Instant.ofEpochSecond(1069965734))
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainImageId(null)
                .build();
    }

    public AdvertisementDto getAdvertisementDtoForTest(Long id) {
        PersonDto person1 = getPersonDtoForTest(null);
        return AdvertisementDto.builder()
                .id(id)
                .person(person1)
                .categoryId(null)
                .topParamId(null)
                .createdDate(10L)
                .cost(100)
                .city("Minsk")
                .header("red fur")
                .description("new red fur")
                .status("ACTIVE")
                .mainPictureId(null)
                .build();
    }
}
