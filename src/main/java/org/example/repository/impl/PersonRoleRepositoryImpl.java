package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.PersonRole;
import org.example.entity.Role;
import org.example.enums.RoleEnum;
import org.example.repository.PersonRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonRoleRepositoryImpl implements PersonRoleRepository {
    private static final Logger logger = LoggerFactory.getLogger(PersonRoleRepositoryImpl.class);
    public static final String READ_ROLE = "select * from coffer.role where person_id=?";
    private static final String INSERT_PERSON_ROLE = "insert into person_role (person_id, role_id) values (?, ?)";
    private static final String DELETE_ROLES = "delete from person_role where person_id=?";
    private final Connection connection;

    @Override
    public PersonRole create(PersonRole entity) {
        logger.info("create person role");
        String query = INSERT_PERSON_ROLE;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, entity.getPersonId());
            statement.setLong(2, entity.getRoleId());
            statement.executeUpdate(query);
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
        String query = READ_ROLE;
        PersonRole.PersonRoleBuilder personRole = PersonRole.builder();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    personRole
                            .personId(resultSet.getLong("person_id"))
                            .roleId(resultSet.getLong("role_id"));
                }
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
