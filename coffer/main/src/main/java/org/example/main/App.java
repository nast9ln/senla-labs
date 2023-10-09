package org.example.main;

import org.example.di.ApplicationStart;
import org.example.di.context.ApplicationContext;
import org.example.di.exception.BeanNotFoundException;
import org.example.main.controller.PersonController;
import org.example.main.dto.PersonDto;
import org.example.main.service.impl.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException, BeanNotFoundException, InstantiationException {
        ApplicationStart.run("org.example.main");

        logger.info("slf4j ExampleApp started.");

        PersonService personService = ApplicationContext.getBean(PersonService.class).orElseThrow(BeanNotFoundException::new);
        System.out.println(personService.getPersonRepository() + "!!");
        System.out.println(personService.getPersonDtoMapper() + "!");

        ApplicationContext.getContext().forEach((key, value) -> System.out.println(key + " : " + value));

        PersonController personController = ApplicationContext.getBean(PersonController.class).orElseThrow(BeanNotFoundException::new);
        System.out.println(personController.getPersonService());
        String result = personController.execute(new PersonDto());
        System.out.println(result);
    }
}

