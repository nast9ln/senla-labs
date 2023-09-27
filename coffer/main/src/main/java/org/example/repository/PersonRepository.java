package org.example.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Person;
import org.example.service.CRUD;

import java.util.HashMap;
import java.util.Map;
@Slf4j
public class PersonRepository implements CRUD<Person> {


    private static Map<Long, Person> database = new HashMap<>();

    public String execute(Person person) {
        log.info("execute");
        return person.execute();
    }

    @Override
    public void create(Person entity) {
        log.info("create");
        database.put(entity.getId(), entity);
    }

    @Override
    public Person read(Long id) {
        log.info("read");
        return database.getOrDefault(id, new Person());
    }

    @Override
    public void update(Person dto) {
        log.info("update");
        database.put(dto.getId(), dto);
    }

    @Override
    public void delete(Long id) {
        log.info("delete");
        database.remove(id);
    }
}