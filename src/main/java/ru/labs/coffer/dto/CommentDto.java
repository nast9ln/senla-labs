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
public class CommentDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("personId")
    private Long personId;
    @JsonProperty("advertisementId")
    private Long advertisementId;
    @JsonProperty("text")
    private String text;
    @JsonProperty("createdDate")
    private Instant createdDate;
    @JsonProperty("isDeleted")
    private boolean isDeleted;
}
