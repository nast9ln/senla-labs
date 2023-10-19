package org.example;


import org.example.controller.PersonController;
import org.example.dto.PersonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class App  {
    private static final Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException, InstantiationException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");
        logger.info("slf4j ExampleApp started.");

        PersonController personController = context.getBean(PersonController.class);
        PersonDto personDto = personController.read(1L);
        System.out.println(personDto);
        personController.delete(2L);
        personDto.setEmail("newMail");
        personController.update(personDto);
        System.out.println(personController.read(1L));
        System.out.println(personController.parseToJson(personDto));
    }
}

