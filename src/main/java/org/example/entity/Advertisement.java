package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Advertisement {
    private Long id;
    private Long personId;
    private Long categoryId;
    private Long topParamId;
    private LocalDateTime createdDate;
    private int cost;
    private String city;
    private String header;
    private String description;
    private String status;
    private Long mainImageId;
    private boolean isDeleted;

    public Advertisement() {
    }
}
