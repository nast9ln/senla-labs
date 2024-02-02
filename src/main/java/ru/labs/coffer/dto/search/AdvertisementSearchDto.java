package ru.labs.coffer.dto.search;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvertisementSearchDto extends BaseSearchDto {
    private Integer minCost;
    private Integer maxCost;
    private Long categoryId;
    private Long personId;
    private String city;
    private String header;

}
