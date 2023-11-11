package org.example;

import org.example.controller.AdvertisementController;
import org.example.controller.PersonControllerImpl;
import org.example.dto.AdvertisementDto;
import org.example.dto.PersonDto;
import org.example.dto.RoleDto;
import org.example.entity.Advertisement;
import org.example.entity.Person;
import org.example.entity.Role;
import org.example.enums.Gender;
import org.example.enums.RoleEnum;
import org.example.repository.AdvertisementRepository;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@ComponentScan("org.example")
@PropertySource(value = "application.properties")
public class App {
    @Value("${database.url}")
    private static String url;
    @Value("${hibernate.show_sql}")
    private static boolean showSql;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);

        PersonControllerImpl personController = context.getBean(PersonControllerImpl.class);


//        Thread createThread = new Thread(() -> {
//            System.out.println("Create Thread " + Thread.currentThread().getName() + " got a connection");
//            personController.create((PersonDto.builder()
//                    .gender(Gender.WOMAN)
//                    .firstName("THREAD 1")
//                    .lastName("Stuk")
//                    .birthday(LocalDate.of(2005, 1, 14))
//                    .city("Vitebsk")
//                    .phone("+211")
//                    .email("nast9ln@h.com")
//                    .password("1202")
//                    .isDeleted(false)
//                    .roles(Arrays.asList(new RoleDto(RoleEnum.ADMIN), new RoleDto(RoleEnum.USER)))
//                    .build()));
//        });
//
//        Thread createThread2 = new Thread(() -> {
//            System.out.println("Create Thread " + Thread.currentThread().getName() + " got a connection");
//            personController.create((PersonDto.builder()
//                    .gender(Gender.WOMAN)
//                    .firstName("THREAD 2")
//                    .lastName("test")
//                    .birthday(LocalDate.of(2005, 1, 14))
//                    .city("Vitebsk")
//                    .phone("+211")
//                    .email("nast9ln@h.com")
//                    .password("1202")
//                    .isDeleted(false)
//                    .roles(Arrays.asList(new RoleDto(RoleEnum.ADMIN), new RoleDto(RoleEnum.USER)))
//                    .build()));
//        });
//
//        Thread createThread3 = new Thread(() -> {
//            System.out.println("Create Thread " + Thread.currentThread().getName() + " got a connection");
//            personController.create((PersonDto.builder()
//                    .gender(Gender.WOMAN)
//                    .firstName("THREAD 3")
//                    .lastName("null")
//                    .birthday(LocalDate.of(2005, 1, 14))
//                    .city("Vitebsk")
//                    .phone("+211")
//                    .email("nast9ln@h.com")
//                    .password("1202")
//                    .isDeleted(false)
//                    .roles(Arrays.asList(new RoleDto(RoleEnum.ADMIN), new RoleDto(RoleEnum.USER)))
//                    .build()));
//        });
//
//        Thread deleteThread = new Thread(() -> {
//            System.out.println("Delete Thread " + Thread.currentThread().getName() + " got a connection");
//            personController.delete(300L);
//        });
//
//        createThread.start();
//        createThread2.start();
//        createThread3.start();
//        deleteThread.start();
//
//        try {
//            createThread.join();
//            createThread2.join();
//            createThread3.join();
//            deleteThread.join();
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }



        //Создание нового пользователя
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


        //  Создание нового объявления
//        AdvertisementController advertisementController = context.getBean(AdvertisementController.class);
//        advertisementController.create(AdvertisementDto.builder()
//                .personId(313L)
//                .categoryId(2L)
//                .topParamId(null)
//                .createdData(LocalDateTime.now())
//                .cost(100)
//                .city("Minsk")
//                .header("red fur")
//                .description("new red fur")
//                .status("ACTIVE")
//                .mainPictureId(null)
//                .isDeleted(false)
//        .build());


//        //получаем пользователя, выводим его объявления
//        PersonDto readPerson = new PersonDto();
//        readPerson=personController.read(1L);
//        System.out.println(readPerson.getFirstName());


        //выводим все объявления
//        for (AdvertisementDto advertisement : readPerson.getAdvertisementDto())
//            System.out.println(advertisement);
//
//        //Чтение ролей у пользователя
//        for (RoleDto role : readPerson.getRoles())
//            System.out.println(role.getName());

//        //Вносим изменения  - устанавливаем другой город
//        AdvertisementDto advertisementNew = readPerson.getAdvertisementDto().get(0);
//        advertisementNew.setCity("minsk");
//        advertisementController.update(advertisementNew);
//
//        //выводим все объявления после того, как одно обновили
//        for (AdvertisementDto advertisement : readPerson.getAdvertisementDto())
//            System.out.println(advertisement);
        context.close();
    }
}

