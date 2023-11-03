package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.Advertisement;
import org.example.entity.Person;
import org.example.entity.Role;
import org.example.enums.Gender;
import org.example.enums.RoleEnum;
import org.example.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor

public class PersonRepositoryImpl implements PersonRepository {
    private static final Logger logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    private static final String READ_PERSON = "select * from person where id= ? and is_deleted=false";
    private static final String UPDATE_PERSON = "update person set gender=?, first_name=?,last_name=?, birthday=?, city=?," +
            "phone=?, email=?, avatar = null, password=?, is_deleted=?  where id=? and is_deleted=false";
    private static final String READ_PERSON_ROLE = "select * from person_role join role on person_role.role_id=role.id where " +
            "person_role.person_id=?";
    private static final String READ_ADVERTISEMENT = "select * from advertisement where person_id=? and is_deleted=false";
    private static final String INSERT_PERSON = "insert into person (gender, first_name, last_name, birthday, city, phone, email, password,is_deleted) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_PERSON = "update person set is_deleted=true where id=?;";

    private final Connection connection;

    @Override
    public Person create(Person entity) {
        logger.info("create");
        try {
            long personId = insertPerson(entity);
            entity.setId(personId);
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create person", e);
        }
    }

    private long insertPerson(Person entity) throws SQLException {
        String query = PersonRepositoryImpl.INSERT_PERSON;
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getGender().name());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setDate(4, Date.valueOf(entity.getBirthday()));
            statement.setString(5, entity.getCity());
            statement.setString(6, entity.getPhone());
            statement.setString(7, entity.getEmail());
            statement.setString(8, entity.getPassword());
            statement.setBoolean(9, entity.isDeleted());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("Creating person failed, no ID obtained.");
            }
        }
    }


    @Override
    public Person read(Long id) {
        logger.info("read");
        Person.PersonBuilder person = Person.builder();
        try {
            String query = READ_PERSON;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        person
                                .id(resultSet.getLong("id"))
                                .gender(Gender.valueOf(resultSet.getString("gender")))
                                .firstName(resultSet.getString("first_name"))
                                .lastName(resultSet.getString("last_name"))
                                .birthday(LocalDate.parse(resultSet.getString("birthday")))
                                .city(resultSet.getString("city"))
                                .phone(resultSet.getString("phone"))
                                .email(resultSet.getString("email"))
                                .password(resultSet.getString("password"))
                                .isDeleted(resultSet.getBoolean("is_deleted"));
                    }
                }

                query = READ_PERSON_ROLE;
                try (PreparedStatement roleStatement = connection.prepareStatement(query)) {
                    roleStatement.setLong(1, id);
                    try (ResultSet roleResultSet = roleStatement.executeQuery()) {
                        List<Role> roles = new ArrayList<>();
                        while (roleResultSet.next()) {
                            Role role = new Role();
                            role.setId(roleResultSet.getLong("id"));
                            role.setName(RoleEnum.valueOf(roleResultSet.getString("name")));
                            roles.add(role);
                        }
                        person.roles(roles);
                    }
                }

//                List<Advertisement> advertisements = new ArrayList<>();
//                query = READ_ADVERTISEMENT;
//                try (PreparedStatement advStatement = connection.prepareStatement(query)) {
//                    advStatement.setLong(1, id);
//                    try (ResultSet advResultSet = advStatement.executeQuery()) {
//                        while (advResultSet.next()) {
//                            Advertisement advertisement = new Advertisement();
//                            advertisement.setId(advResultSet.getLong("id"));
//                            advertisement.setPersonId(advResultSet.getLong("person_id"));
//                            advertisement.setCategoryId(advResultSet.getLong("category_id"));
//                            advertisement.setTopParamId(Optional.ofNullable(advResultSet.getString("top_param_id")).map(Long::getLong).orElse(null));
//                            advertisement.setCreatedDate(LocalDateTime.parse(advResultSet.getString("created_data"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSS]")));
//                            advertisement.setCost(advResultSet.getInt("cost"));
//                            advertisement.setCity(advResultSet.getString("city"));
//                            advertisement.setHeader(advResultSet.getString("header"));
//                            advertisement.setDescription(advResultSet.getString("description"));
//                            advertisement.setStatus(advResultSet.getString("status"));
//                            advertisement.setMainImageId(advResultSet.getLong("main_image_id"));
//                            advertisement.setDeleted(advResultSet.getBoolean("is_deleted"));
//                            advertisements.add(advertisement);
//                        }
//                        person.advertisements(advertisements);
//                    }
//                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person.build();
    }

    @Override
    public Person update(Person entity) {
        logger.info("update");
        try {
            String query = UPDATE_PERSON;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, entity.getGender().name());
                statement.setString(2, entity.getFirstName());
                statement.setString(3, entity.getLastName());
                statement.setDate(4, Date.valueOf(entity.getBirthday()));
                statement.setString(5, entity.getCity());
                statement.setString(6, entity.getPhone());
                statement.setString(7, entity.getEmail());
                statement.setString(8, entity.getPassword());
                statement.setBoolean(9, entity.isDeleted());
                statement.setLong(10, entity.getId());
                statement.executeUpdate();
            }
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        logger.info("delete");
        try (PreparedStatement deletePersonStatement = connection.prepareStatement(DELETE_PERSON)) {
            deletePersonStatement.setLong(1, id);
            deletePersonStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}