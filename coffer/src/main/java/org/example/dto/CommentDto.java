package org.example.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class CommentDto {
    private Long id;
    private Long userId;
    private Long advertisementId;
    private String text;
    private LocalDateTime createdData;
}
