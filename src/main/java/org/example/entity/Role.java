package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.RoleEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;
    private RoleEnum name;
}
