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
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@UtilityClass
public class DataFactory {

    public Person getPersonForTest(Long id) {
        return Person.builder()
                .id(id)
                .gender(Gender.WOMAN)
                .firstName("test1")
                .lastName("test1")
                .birthday(Instant.ofEpochSecond(1069965734))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new Role(RoleEnum.USER)))
                .build();
    }

    public PersonDto getPersonDtoForTest(Long id) {
        return PersonDto.builder()
                .id(id)
                .gender(Gender.WOMAN)
                .firstName("test1")
                .lastName("test1")
                .birthday(Instant.ofEpochSecond(1069965734))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Set.of(new RoleDto(RoleEnum.USER)))
                .build();
    }

    public Advertisement getAdvertisementForTest(Long id) {
        Person person1 = getPersonForTest(1L);
        return Advertisement.builder()
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
                .build();
    }

    public AdvertisementDto getAdvertisementDtoForTest(Long id) {
        PersonDto person1 = getPersonDtoForTest(1L);
        return AdvertisementDto.builder()
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
                .build();
    }
}
