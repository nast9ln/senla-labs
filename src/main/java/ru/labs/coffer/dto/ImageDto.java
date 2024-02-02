package ru.labs.coffer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("advertisementId")
    private Long advertisementId;
    @JsonProperty("png")
    private byte[] png;
}
