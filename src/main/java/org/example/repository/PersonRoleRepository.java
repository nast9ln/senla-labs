package org.example.repository;

import org.example.entity.PersonRole;
import org.example.entity.Role;
import org.example.service.CRUD;

import java.sql.SQLException;
import java.util.List;

public interface PersonRoleRepository extends CRUD<PersonRole> {

    void createRelation(long personId, List<Role> roles) throws SQLException;

}
