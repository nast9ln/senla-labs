package ru.labs.coffer.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.labs.coffer.dto.MessageDto;
import ru.labs.coffer.entity.Message;

@Mapper(uses = {PersonMapper.class})
public interface MessageMapper {

    @InheritInverseConfiguration
    Message toEntity(MessageDto dto);

    @Mapping(target = "recipientId", source = "recipient.id")
    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "advertisementId", source = "advertisement.id")
    MessageDto toDto(Message entity);
}
