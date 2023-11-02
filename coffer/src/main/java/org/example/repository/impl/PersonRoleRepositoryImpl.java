package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.PersonRole;
import org.example.entity.Role;
import org.example.enums.RoleEnum;
import org.example.repository.PersonRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonRoleRepositoryImpl implements PersonRoleRepository {
    private static final Logger logger = LoggerFactory.getLogger(PersonRoleRepositoryImpl.class);
    private final Connection connection;
    private static final String INSERT_PERSON_ROLE = "insert into person_role (person_id, role_id) values (?, ?)";
    private static final String DELETE_ROLES = "delete from person_role where person_id=?";

    @Override
    public PersonRole create(PersonRole entity) {
        try {
            Statement statement;
            String query = String.format("insert into coffer.person_role(person_id, role_id) values (%s, %s)", entity.getPersonId(), entity.getRoleId());
            statement = connection.createStatement();
            statement.executeUpdate(query);
            logger.info("create person role");
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createRelation(long personId, List<Role> roles) throws SQLException {
        List<Role> rolesFromDatabase = getRolesByNames(roles);
        if (!roles.isEmpty()) {
            String query = String.format(INSERT_PERSON_ROLE);
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (Role role : rolesFromDatabase) {
                    statement.setLong(1, personId);
                    statement.setLong(2, role.getId());
                    statement.executeUpdate();
                }
            }
        }
    }

    private List<Role> getRolesByNames(List<Role> roles) throws SQLException {
        List<Role> rolesFromDatabase = new ArrayList<>();
        if (!roles.isEmpty()) {
            String query = "select * from role where name in (" + String.join(",", Collections.nCopies(roles.size(), "?")) + ")";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                for (int i = 0; i < roles.size(); i++) {
                    statement.setString(i + 1, roles.get(i).getName().name());
                }
                try (ResultSet rolesSet = statement.executeQuery()) {
                    while (rolesSet.next()) {
                        Role role = new Role();
                        role.setName(RoleEnum.valueOf(rolesSet.getString("name")));
                        role.setId(rolesSet.getLong("id"));
                        rolesFromDatabase.add(role);
                    }
                }
            }
        }
        return rolesFromDatabase;
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
    public PersonRole update(PersonRole dto) {
        return dto;
    }

    @Override
    public void delete(Long id) {
        try {
            String query = DELETE_ROLES;
            try (PreparedStatement deletePersonRoleStatement = connection.prepareStatement(query)) {
                deletePersonRoleStatement.setLong(1, id);
                deletePersonRoleStatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
