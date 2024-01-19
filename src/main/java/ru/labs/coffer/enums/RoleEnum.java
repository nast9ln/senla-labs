package ru.labs.coffer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ROLE_USER(1L),
    ROLE_ADMIN(2L);

    private Long code;
}
