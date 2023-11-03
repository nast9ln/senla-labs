package org.example;


import org.example.config.JDBCPostgreSQLConnectionHolder;
import org.example.controller.PersonControllerImpl;
import org.example.dto.PersonDto;
import org.example.dto.RoleDto;
import org.example.entity.Person;
import org.example.enums.Gender;
import org.example.enums.RoleEnum;
import org.example.repository.AdvertisementRepository;
import org.example.repository.PersonRepository;
import org.example.service.impl.PersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException, InstantiationException, SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");

        logger.info("slf4j ExampleApp started.");

        JDBCPostgreSQLConnectionHolder connect = context.getBean(JDBCPostgreSQLConnectionHolder.class);
        PersonRepository personRepository = context.getBean(PersonRepository.class);
        AdvertisementRepository advertisementRepository = context.getBean(AdvertisementRepository.class);
    //    PersonControllerImpl personController = context.getBean(PersonControllerImpl.class);
        PersonServiceImpl personService = context.getBean(PersonServiceImpl.class);



        int numThreads = 5;
        Thread[] threads = new Thread[numThreads];

        PersonControllerImpl personController = context.getBean(PersonControllerImpl.class);
        for (int i=0; i < numThreads; i++) {
            threads[i]=new Thread(()-> {
                Connection connection = connect.getConnection();
                if(connection!=null) {
                    System.out.println("thread" + Thread.currentThread().getName() + "got a connection");
                    personController.create(PersonDto.builder()
                .gender(Gender.WOMAN)
                .firstName("Lina")
                .lastName("Stuk")
                .birthday(LocalDate.of(2005, 01, 14))
                .city("Vitebsk")
                .phone("+211")
                .email("nast9ln@h.com")
                .password("1202")
                .isDeleted(false)
                .roles(Arrays.asList(new RoleDto(RoleEnum.ADMIN), new RoleDto(RoleEnum.USER)))
                .build());

                }
                else System.out.println("thread"+ Thread.currentThread().getName() + "failed");
            });
            threads[i].start();
        }

        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }




        //Создание нового пользователя
//        personController.create(PersonDto.builder()
//                .gender(Gender.WOMAN)
//                .firstName("Lina")
//                .lastName("Stuk")
//                .birthday(LocalDate.of(2005, 01, 14))
//                .city("Vitebsk")
//                .phone("+211")
//                .email("nast9ln@h.com")
//                .password("1202")
//                .isDeleted(false)
//                .roles(Arrays.asList(new RoleDto(RoleEnum.ADMIN), new RoleDto(RoleEnum.USER)))
//                .build());
//
//        PersonDto personRead = personController.read(10L);
//        System.out.println(personRead);

       // personController.delete(25L);

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

        //      personController.delete(3L);
//        Person readPerson = new Person();
//        readPerson=personRepository.read(10L);
//        System.out.println(readPerson.getFirstName());


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



//        PersonDto personUpdate = personRead;
//        personUpdate.setCity("Moskow");
//        personController.update(personUpdate);

//        personRepository.delete(13L);


        context.close();
    }
}

