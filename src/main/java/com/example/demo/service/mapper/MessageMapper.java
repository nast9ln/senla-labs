package com.example.demo.service.mapper;

import com.example.demo.dto.MessageDto;
import com.example.demo.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MessageMapper {

    @Mapping(target = "sender.id", source = "senderId")
    @Mapping(target = "advertisement.id", source = "advertisementId")
    Message toEntity(MessageDto dto);

    MessageDto toDto(Message entity);

}
