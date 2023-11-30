package org.example.util;

import lombok.RequiredArgsConstructor;
import org.example.entity.Advertisement;
import org.example.entity.Person;
import org.example.entity.Role;
import org.example.enums.Gender;
import org.example.enums.RoleEnum;
import org.example.repository.AdvertisementRepository;
import org.example.repository.PersonRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DatabaseUtil {

    private final PersonRepository personRepository;
    private final AdvertisementRepository advertisementRepository;

    public Person createPerson() {
        Person build = Person.builder()
                .gender(Gender.WOMAN)
                .firstName("test1")
                .lastName("test1")
                .birthday(Instant.ofEpochSecond(1069965734))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .roles(Set.of(new Role(RoleEnum.USER)))
                .build();
        return personRepository.save(build);
    }

    public Advertisement createAdvertisement(){
        Person person = createPerson();
        Advertisement advertisement = Advertisement.builder()
                .person(person)
                .categoryId(null)
                .topParamId(null)
                .createdDate(LocalDateTime.now().toInstant(ZoneOffset.UTC))
                .cost(111)
                .city("MinskTest")
                .header("red furTest")
                .description("new red furTest")
                .status("ACTIVETest")
                .mainImageId(null)
                .build();
        return advertisementRepository.create(advertisement);
    }

}
