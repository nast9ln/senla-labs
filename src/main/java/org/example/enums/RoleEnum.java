package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    USER(1L),
    ADMIN(2L);

    private Long code;

}
