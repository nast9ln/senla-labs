package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.TopParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDto implements Serializable {
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("person")
    private PersonDto person;

    @JsonProperty("category")
    private Long category;

    @JsonProperty("mainPictureId")
    private Long mainPictureId;

    @JsonProperty("topParamId")
    private TopParam topParamId;

    @JsonProperty("createdDate")
    private Long createdData;

    @JsonProperty("cost")
    private int cost;

    @JsonProperty("city")
    private String city;

    @JsonProperty("header")
    private String header;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("isDeleted")
    private boolean isDeleted;
}

