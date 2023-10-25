package org.example;


import org.example.config.JDBCPostgreSQLConnectImpl;
import org.example.controller.PersonController;
import org.example.dto.PersonDto;
import org.example.entity.Person;
import org.example.entity.Role;
import org.example.enums.Gender;
import org.example.enums.NameRole;
import org.example.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;


public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException, InstantiationException, SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");

        logger.info("slf4j ExampleApp started.");

        JDBCPostgreSQLConnectImpl connect = context.getBean(JDBCPostgreSQLConnectImpl.class);
        //connect.connect();
        PersonRepository personRepository = context.getBean(PersonRepository.class);
//        personRepository.create(Person.builder()
//                .gender(Gender.MAN)
//                .firstName("Kostya")
//                .lastName("Krylov")
//                .birthday(LocalDate.of(1999, 10, 12))
//                .city("Moskow")
//                .phone("+333")
//                .email("mdfmmm@h.com")
//                .password("12sf12")
//                .isDeleted(false)
//                .roles(Arrays.asList(new Role(null, NameRole.ADMIN), new Role(null,NameRole.USER)))
//                .build());
//        Person person_read = personRepository.read(28L);
//        System.out.println(person_read.getId().toString()+ person_read.getFirstName().toString());
//        for (Role role : person_read.getRoles())
//            System.out.println(role.getName());
        //   personRepository.create(new Person(11L, Gender.WOMAN, "Milly", "Sun", LocalDateTime.of(2000, 10, 12, 0, 0),"Los","+377","mailm@mail", "2234",false));

  /*      Person gPerson= new Person();
        gPerson=personRepository.read(16L);
        System.out.println(gPerson.getFirstName()+"!!!!!!!!!!11111!");*/
//        personRepository.update((Person.builder()
//                .id(9L)
//                .gender(Gender.MAN)
//                .firstName("newName")
//                .lastName("Krylov")
//                .birthday(LocalDate.of(1999, 10, 12))
//                .city("Moskow")
//                .phone("+333")
//                .email("mdfmmm@h.com")
//                .password("12sf12")
//                .isDeleted(false)
//                .roles(Arrays.asList(new Role(null, NameRole.ADMIN), new Role(null,NameRole.USER)))
//                .build()));
           personRepository.delete(28L);


//        PersonController personController = context.getBean(PersonController.class);
//        PersonDto personDto = personController.read(1L);
//        System.out.println(personDto);
//        personController.delete(2L);
//        personDto.setEmail("newMail");
//        //     personController.update(personDto);
//        System.out.println(personController.read(1L));
//        System.out.println(personController.parseToJson(personDto));
//        System.out.println(personController.execute(personDto));


    }
}

