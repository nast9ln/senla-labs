package ru.labs.coffer.dto.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageSearchDto extends BaseSearchDto {

    private Long senderId;
    private Long recipientId;
    private Long advertisementId;

}
