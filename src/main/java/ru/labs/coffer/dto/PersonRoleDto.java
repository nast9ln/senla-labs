package ru.labs.coffer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonRoleDto {
    @JsonProperty("person_id")
    private Long personId;
    @JsonProperty("role_id")
    private Long roleId;
}
