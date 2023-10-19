package org.example.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class Comment {
    private Long id;
    private Long userId;
    private Long advertisementId;
    private String text;
    private LocalDateTime createdData;
}
