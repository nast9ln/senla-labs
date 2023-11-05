package org.example.dto;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private Long userId;
    private Long advertisementId;
    private String text;
    private LocalDateTime createdData;
}
