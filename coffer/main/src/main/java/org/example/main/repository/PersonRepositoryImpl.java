package org.example.main.repository;

import org.example.di.annotation.Component;
import org.example.main.config.ParametersHolder;
import org.example.main.config.ParametersHolderImpl;
import org.example.main.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component
public class PersonRepositoryImpl implements PersonRepository {
    private static final Logger logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    private static final ParametersHolder parametersHolder = new ParametersHolderImpl();
    private static final Map<Long, Person> database = new HashMap<>();

    public PersonRepositoryImpl() {
    }

    public String execute(Person person) {
        //  return person.execute();
        return parametersHolder.getSomeText();
    }

    @Override
    public void create(Person entity) {
        logger.info("create");
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