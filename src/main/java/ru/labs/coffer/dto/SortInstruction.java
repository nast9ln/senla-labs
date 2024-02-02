package ru.labs.coffer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SortInstruction implements Serializable {
    @Schema(description = "направление сортировки",
            example = "ASC")
    private Sort.Direction direction;

    @Schema(description = "Поле для сортировки результатов",
            example = "role")
    private String field;

    public static SortInstruction of(Sort.Direction direction, String field) {
        return new SortInstruction(direction, field);
    }
}
