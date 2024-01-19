package ru.labs.coffer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.labs.coffer.enums.RoleEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private RoleEnum name;

    public RoleDto(RoleEnum name) {
        this.name = name;
    }
}
