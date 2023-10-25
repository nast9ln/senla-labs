package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.PersonRole;
import org.example.repository.PersonRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
public class PersonRoleRepositoryImpl implements PersonRoleRepository {
    private static final Logger logger = LoggerFactory.getLogger(PersonRoleRepositoryImpl.class);
    private final Connection connection;

    @Override
    public void create(PersonRole entity) {
        try {
            Statement statement;
            String query = String.format("insert into coffer.person_role(person_id, role_id) values (%s, %s)", entity.getPersonId(), entity.getRoleId());
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info("create person role");
    }

    @Override
    public PersonRole read(Long id) {
        logger.info("read role");
        PersonRole.PersonRoleBuilder personRole = PersonRole.builder();
        try {
            String query = String.format("select * from coffer.role where person_id=%s", id);
            Statement statement;
            ResultSet resultSet;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                personRole
                        .personId(resultSet.getLong("person_id"))
                        .roleId(resultSet.getLong("role_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personRole.build();
    }

    @Override
    public void update(PersonRole dto) {

    }

    @Override
    public void delete(Long id) {

    }
}
