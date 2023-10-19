package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {
    private Long id;
    private Long userId;
    private Long categoryId;
    private Long mainPictureId;
    private Long topParamId;
    private LocalDateTime createdDate;
    private int cost;
    private String city;
    private String header;
    private String description;
    private String type;
    private String status;
    private boolean isDeleted;
}
