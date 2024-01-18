package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopParamDto {
    private Long id;
    private Instant timeTopStart;
    private Integer timeInTop;
    private boolean isTop;
}
