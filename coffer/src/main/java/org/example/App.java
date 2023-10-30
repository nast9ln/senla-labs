package org.example;


import org.example.config.JDBCPostgreSQLConnectionHolder;
import org.example.controller.PersonControllerImpl;
import org.example.entity.Person;
import org.example.repository.AdvertisementRepository;
import org.example.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;


public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException, InstantiationException, SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");

        logger.info("slf4j ExampleApp started.");

        JDBCPostgreSQLConnectionHolder connect = context.getBean(JDBCPostgreSQLConnectionHolder.class);
        PersonRepository personRepository = context.getBean(PersonRepository.class);
        AdvertisementRepository advertisementRepository = context.getBean(AdvertisementRepository.class);

        //    personRepository.delete(12L);
        //Создание нового пользователя
//        personRepository.create(Person.builder()
//                .gender(Gender.WOMAN)
//                .firstName("Anastasia")
//                .lastName("Stuk")
//                .birthday(LocalDate.of(2005, 01, 14))
//                .city("Vitebsk")
//                .phone("+211")
//                .email("nast9ln@h.com")
//                .password("1202")
//                .isDeleted(false)
//                .roles(Arrays.asList(new Role(null, NameRole.ADMIN), new Role(null,NameRole.USER)))
//                .build());


      //  Создание нового объявления
//        advertisementRepository.create(Advertisement.builder()
//                .personId(13L)
//                .categoryId(2L)
//                .topParamId(null)
//                        .createdDate(LocalDateTime.now())
//                        .cost(100)
//                        .city("Minsk")
//                        .header("red fur")
//                        .description("new red fur")
//                        .status("ACTIVE")
//                        .mainImageId(null)
//                        .isDeleted(false)
//        .build());


//        //получаем пользователя, выводим его объявления

        PersonControllerImpl personController = context.getBean(PersonControllerImpl.class);
        personController.delete(3L);
        Person readPerson = new Person();
        readPerson=personRepository.read(10L);
        System.out.println(readPerson.getFirstName());

        //выводим все объявления
//        for (Advertisement advertisement : readPerson.getAdvertisements())
//            System.out.println(advertisement);
//
//        //Чтение ролей у пользователя
//        for (Role role : readPerson.getRoles())
//            System.out.println(role.getName());

//        //Вносим изменения  - устанавливаем другой город
//        Advertisement advertisementNew = readPerson.getAdvertisements().get(0);
//        advertisementNew.setCity("minsk");
//        advertisementRepository.update(advertisementNew);
//
//
//        //выводим все объявления после того, как одно обновили
//        for (Advertisement advertisement : readPerson.getAdvertisements())
//            System.out.println(advertisement);


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

//        Person personUpdate = readPerson;
//        personUpdate.setCity("Moskow");
//        personRepository.update(personUpdate);

//        personRepository.delete(13L);


    }
}

