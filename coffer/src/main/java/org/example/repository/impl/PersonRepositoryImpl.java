package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.config.ParametersHolder;
import org.example.entity.Person;
import org.example.entity.Role;
import org.example.enums.Gender;
import org.example.enums.NameRole;
import org.example.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {
    private static final Logger logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    public static final String READ_PERSON = "select * from coffer.person where id= %s";
    public static final String UPDATE_PERSON = "update coffer.person set gender= '%s', first_name='%s',last_name='%s', city='%s'," +
            "phone='%s', email='%s', avatar = null, password='%s',is_deleted=%s  where id=%s";
    public static final String DELETE_PERSON = "delete from coffer.person where id=%s";
    public static final String DELETE_ROLES = "delete from coffer.person_role where person_id=%s";

    public static final String DELETE_PERSON_ROLE = "delete from coffer.person_role where person_id=%s";
    public static final String INSERT_ROLE = "insert into coffer.person_role (person_id, role_id) values (%s, %s)";
    public static final String READ_PERSON_ROLE = "select * from coffer.person_role join coffer.role on person_role.role_id=role.id where " +
            "person_role.person_id=%s";
    public static final String INSERT_PERSON = "INSERT INTO coffer.person (gender, first_name, last_name, birthday, city, phone, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_PERSON_ROLE = "INSERT INTO coffer.person_role (person_id, role_id) VALUES (?, ?)";

    private final ParametersHolder parametersHolder;
    private final Connection connection;

    public String execute(Person person) {
        return parametersHolder.getSomeText();
    }

    @Override
    public void create(Person entity) {
        try {
            long personId = insertPerson(entity);
            List<Role> rolesFromDatabase = getRolesByNames(entity.getRoles());
            insertPersonRoles(personId, rolesFromDatabase);
            entity.setId(personId);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create person", e);
        }
        logger.info("Created person: {}", entity);
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
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            } else {
                throw new SQLException("Creating person failed, no ID obtained.");
            }
        }
    }

    private List<Role> getRolesByNames(List<Role> roles) throws SQLException {
        List<Role> rolesFromDatabase = new ArrayList<>();
        if (!roles.isEmpty()) {
            String query = "SELECT * FROM coffer.role WHERE name IN (" + String.join(",", Collections.nCopies(roles.size(), "?")) + ")";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < roles.size(); i++) {
                    statement.setString(i + 1, roles.get(i).getName().name());
                }
                try (ResultSet rolesSet = statement.executeQuery()) {
                    while (rolesSet.next()) {
                        Role role = new Role();
                        role.setName(NameRole.valueOf(rolesSet.getString("name")));
                        role.setId(rolesSet.getLong("id"));
                        rolesFromDatabase.add(role);
                    }
                }
            }
        }
        return rolesFromDatabase;
    }

    private void insertPersonRoles(long personId, List<Role> roles) throws SQLException {
        if (!roles.isEmpty()) {
            String query = String.format(PersonRepositoryImpl.INSERT_PERSON_ROLE);
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (Role role : roles) {
                    statement.setLong(1, personId);
                    statement.setLong(2, role.getId());
                    statement.executeUpdate();
                }
            }
        }
    }

    @Override
    public Person read(Long id) {
        logger.info("read");
        Person.PersonBuilder person = Person.builder();
        try {
            String query = String.format(READ_PERSON, id);
            Statement statement;
            ResultSet resultSet;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
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
            query = String.format(READ_PERSON_ROLE, id);
            resultSet = statement.executeQuery(query);
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong("id"));
                role.setName(NameRole.valueOf(resultSet.getString("name")));
                roles.add(role);
            }
            person.roles(roles);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person.build();
    }

    @Override
    public void update(Person entity) {
        logger.info("update");
        try {
            Statement statement;
            String query = String.format(UPDATE_PERSON, entity.getGender(), entity.getFirstName(),
                    entity.getLastName(), entity.getCity(), entity.getPhone(), entity.getEmail(), entity.getPassword(), entity.isDeleted(),
                    entity.getId());
            statement = connection.createStatement();
            statement.executeUpdate(query);

            query = String.format(DELETE_ROLES, entity.getId());
            statement.executeUpdate(query);

            for (Role role : entity.getRoles()) {
                String insertRoleQuery = String.format(INSERT_ROLE, entity.getId(), role.getId());
                statement.executeUpdate(insertRoleQuery);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Statement statement;
        //    String query = String.format(DELETE_PERSON, id);
            String query = String.format(DELETE_PERSON_ROLE, id);
            statement = connection.createStatement();
            statement.executeUpdate(query);

            query = String.format(DELETE_PERSON, id);
            statement.executeUpdate(query);
            logger.info("delete");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}