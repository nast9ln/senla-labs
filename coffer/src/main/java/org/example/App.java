package org.example;


import org.example.controller.PersonControllerImpl;
import org.example.dto.PersonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");
        logger.info("slf4j ExampleApp started.");

        PersonControllerImpl personController = context.getBean(PersonControllerImpl.class);
        PersonDto personDto = personController.read(0L);
        System.out.println(personDto);
        personController.delete(1L);
        personDto.setEmail("newMail");
        personController.update(personDto);
        System.out.println(personController.read(0L));
        System.out.println(personController.parseToJson(personDto));
    }
}

