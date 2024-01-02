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
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("sender_id")
    private Long senderId;
    @JsonProperty("advertisement")
    private Long advertisementId;
    @JsonProperty("text")
    private String text;
    @JsonProperty("created_date")
    private Instant createdDate;
    @JsonProperty("isDeleted")
    private boolean isDeleted;
}
