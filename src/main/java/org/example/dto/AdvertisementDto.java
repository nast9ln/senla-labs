package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Person;
import org.example.entity.TopParam;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDto implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("person")
    private PersonDto person;

    @JsonProperty("category")
    private Long category;

    @JsonProperty("mainPictureId")
    private Long mainPictureId;

    @JsonProperty("topParamId")
    private TopParam topParamId;

    @JsonProperty("createdDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdData;

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

