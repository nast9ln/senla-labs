package ru.labs.coffer.mapper;

import org.mapstruct.Mapper;
import ru.labs.coffer.dto.RoleDto;
import ru.labs.coffer.entity.Role;

@Mapper

public interface RoleMapper {
    Role toEntity(RoleDto dto);

    RoleDto toDto(Role entity);
}
