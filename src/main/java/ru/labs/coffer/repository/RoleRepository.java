package ru.labs.coffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.labs.coffer.entity.Role;
import ru.labs.coffer.enums.RoleEnum;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleEnum name);

}
