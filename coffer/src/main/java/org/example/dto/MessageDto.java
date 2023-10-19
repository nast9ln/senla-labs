package org.example.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class MessageDto {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String text;
    private LocalDateTime createdData;
}
