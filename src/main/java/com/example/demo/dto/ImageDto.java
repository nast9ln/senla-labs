package com.example.demo.dto;

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
    @JsonProperty("advertisementDto")
    private AdvertisementDto advertisement;
    @JsonProperty("path")
    private byte[] path;
}
