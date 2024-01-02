package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbstractDto {
    @JsonProperty(value = "id")
    private Long id;
}
