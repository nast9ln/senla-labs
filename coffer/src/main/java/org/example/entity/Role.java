package org.example.entity;

import lombok.*;
import org.example.enums.RoleEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;
    private RoleEnum name;
}
