package ru.labs.coffer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("sender")
    private Long senderId;
    @JsonProperty("recipient")
    private Long recipientId;
    @JsonProperty("advertisementId")
    private Long advertisementId;
    @JsonProperty("text")
    private String text;
    @JsonProperty("created_date")
    private Instant createdDate;
    @JsonProperty("isDeleted")
    private boolean isDeleted;
}
