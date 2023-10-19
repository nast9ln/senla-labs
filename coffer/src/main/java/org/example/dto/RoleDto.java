package org.example.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
public class RoleDto {
    private Long id;
    private String name;
}
