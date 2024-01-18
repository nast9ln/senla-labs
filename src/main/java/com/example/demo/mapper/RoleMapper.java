package com.example.demo.mapper;

import com.example.demo.dto.RoleDto;
import com.example.demo.entity.Role;
import org.mapstruct.Mapper;

@Mapper

public interface RoleMapper {
    Role toEntity(RoleDto dto);

    RoleDto toDto(Role entity);
}
