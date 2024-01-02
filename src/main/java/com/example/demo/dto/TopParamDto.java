package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopParamDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("time_top_start")
    private Instant timeTopStart;
    @JsonProperty("time_in_top")
    private Integer timeInTop;
    @JsonProperty("is_top")
    private boolean isTop;
}
