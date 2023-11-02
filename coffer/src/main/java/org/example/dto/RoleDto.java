package org.example.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.enums.RoleEnum;

@Data
@RequiredArgsConstructor
public class RoleDto {
    private Long id;
    private String name;

    public RoleDto(RoleEnum name) {
        this.name = name.name();
    }
}
