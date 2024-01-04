package com.example.demo.dto;

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
    @JsonProperty("person")
    private PersonDto person;
    @JsonProperty("advertisement")
    private AdvertisementDto advertisement;
    @JsonProperty("text")
    private String text;
    @JsonProperty("createdData")
    private Instant createdDate;
    @JsonProperty("isDeleted")
    private boolean isDeleted;
}
