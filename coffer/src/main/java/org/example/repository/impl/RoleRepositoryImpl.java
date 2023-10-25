package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.Role;
import org.example.enums.NameRole;
import org.example.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
    private static final Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);
    private final Connection connection;

    @Override
    public void create(Role entity) {
        try {
        Statement statement;
            String query = String.format("insert into coffer.person(name) values ('%s')",entity.getName());
            statement=connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info("create role");
    }

    @Override
    public Role read(Long id) {
        logger.info("read role");
        Role.RoleBuilder role = Role.builder();
        try{
        String query = String.format("select * from coffer.role where id=%s", id);
        Statement statement;
        ResultSet resultSet;
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            role
                    .id(resultSet.getLong("id"))
                    .name(NameRole.valueOf(resultSet.getString("name")));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        return role.build();
    }

    @Override
    public void update(Role dto) {
        try {
            Statement statement;
            String query = String.format("update coffer.role set name='%s'", dto.getName());
            statement = connection.createStatement();
            statement.executeUpdate(query);
            logger.info("update");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Statement statement;
            String query = String.format("delete from coffer.person where id=%s", id);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            logger.info("delete");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
