package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.enums.RoleEnum;

@Data
@Builder
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private RoleEnum name;

    public RoleDto(RoleEnum name) {
        this.name = name;
    }
}
