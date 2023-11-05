package org.example;

import org.example.controller.PersonControllerImpl;
import org.example.dto.PersonDto;
import org.example.dto.RoleDto;
import org.example.enums.Gender;
import org.example.enums.RoleEnum;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");

        PersonControllerImpl personController = context.getBean(PersonControllerImpl.class);


        Thread createThread = new Thread(() -> {
            System.out.println("Create Thread " + Thread.currentThread().getName() + " got a connection");
            personController.create((PersonDto.builder()
                    .gender(Gender.WOMAN)
                    .firstName("THREAD 1")
                    .lastName("Stuk")
                    .birthday(LocalDate.of(2005, 1, 14))
                    .city("Vitebsk")
                    .phone("+211")
                    .email("nast9ln@h.com")
                    .password("1202")
                    .isDeleted(false)
                    .roles(Arrays.asList(new RoleDto(RoleEnum.ADMIN), new RoleDto(RoleEnum.USER)))
                    .build()));
        });

        Thread createThread2 = new Thread(() -> {
            System.out.println("Create Thread " + Thread.currentThread().getName() + " got a connection");
            personController.create((PersonDto.builder()
                    .gender(Gender.WOMAN)
                    .firstName("THREAD 2")
                    .lastName("test")
                    .birthday(LocalDate.of(2005, 1, 14))
                    .city("Vitebsk")
                    .phone("+211")
                    .email("nast9ln@h.com")
                    .password("1202")
                    .isDeleted(false)
                    .roles(Arrays.asList(new RoleDto(RoleEnum.ADMIN), new RoleDto(RoleEnum.USER)))
                    .build()));
        });

        Thread createThread3 = new Thread(() -> {
            System.out.println("Create Thread " + Thread.currentThread().getName() + " got a connection");
            personController.create((PersonDto.builder()
                    .gender(Gender.WOMAN)
                    .firstName("THREAD 3")
                    .lastName("null")
                    .birthday(LocalDate.of(2005, 1, 14))
                    .city("Vitebsk")
                    .phone("+211")
                    .email("nast9ln@h.com")
                    .password("1202")
                    .isDeleted(false)
                    .roles(Arrays.asList(new RoleDto(RoleEnum.ADMIN), new RoleDto(RoleEnum.USER)))
                    .build()));
        });

        Thread deleteThread = new Thread(() -> {
            System.out.println("Delete Thread " + Thread.currentThread().getName() + " got a connection");
            personController.delete(300L);
        });

        createThread.start();
        createThread2.start();
        createThread3.start();
        deleteThread.start();

        try {
            createThread.join();
            createThread2.join();
            createThread3.join();
            deleteThread.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
//        //выводим все объявления после того, как одно обновили
//        for (Advertisement advertisement : readPerson.getAdvertisements())
//            System.out.println(advertisement);
        context.close();
    }
}

