package org.example.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonRole {
    private Long personId;
    private Long roleId;
}
