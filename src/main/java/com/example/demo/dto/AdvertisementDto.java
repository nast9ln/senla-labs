package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.List;

@SuperBuilder
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDto {

    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("personDto")
    private PersonDto person;

    @JsonProperty("categoryDto")
    private CategoryDto category;

    @JsonProperty("mainImage")
    private ImageDto mainImage;

    @JsonProperty("images")
    private List<ImageDto> images;

    @JsonProperty("topParamDto")
    private TopParamDto topParam;

    @JsonProperty("createdDate")
    private Instant createdDate;

    @JsonProperty("cost")
    private Integer cost;

    @JsonProperty("city")
    private String city;

    @JsonProperty("header")
    private String header;

    @NotNull
    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("isDeleted")
    private boolean isDeleted;
}
