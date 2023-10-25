package org.example.entity;

import lombok.*;
import org.example.enums.NameRole;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;
    private NameRole name;
}
