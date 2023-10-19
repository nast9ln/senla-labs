package org.example.repository.impl;

import lombok.AllArgsConstructor;
import org.example.config.ParametersHolder;
import org.example.entity.Person;
import org.example.enums.Gender;
import org.example.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {
    private static final Logger logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    private final ParametersHolder parametersHolder;
    private static final Map<Long, Person> database = new HashMap<>();

    private long firstId = 0;

    @Autowired
    public PersonRepositoryImpl(ParametersHolder parametersHolder) {
        this.parametersHolder = parametersHolder;
        Person person1 = Person.builder()
                .gender(Gender.MAN)
                .firstName("Max")
                .lastName("Krylov")
                .birthday(LocalDateTime.of(2000, 10, 12, 0, 0))
                .city("Vitebsk")
                .phone("+333")
                .email("mmmm@h.com")
                .password("1212")
                .isDeleted(false)
                .build();
        this.create(person1);
        Person person2 = Person.builder()
                .gender(Gender.WOMAN)
                .firstName("Nastya")
                .lastName("Stuk")
                .birthday(LocalDateTime.of(2005, 01, 14, 0, 0))
                .city("Vitebsk")
                .phone("+334")
                .email("nast9ln@vk.com")
                .password("1111")
                .isDeleted(false)
                .build();
        this.create(person2);
    }

    public String execute(Person person) {
        return parametersHolder.getSomeText();
    }

    @Override
    public void create(Person entity) {
        logger.info("create");
        entity.setId(firstId++);
        database.put(entity.getId(), entity);
    }

    @Override
    public Person read(Long id) {
        logger.info("read");
        return database.getOrDefault(id, new Person());
    }

    @Override
    public void update(Person dto) {
        logger.info("update");
        database.put(dto.getId(), dto);
    }

    @Override
    public void delete(Long id) {
        logger.info("delete");
        database.remove(id);
    }
}