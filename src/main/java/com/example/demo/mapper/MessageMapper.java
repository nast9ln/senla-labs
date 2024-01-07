package com.example.demo.mapper;

import com.example.demo.dto.MessageDto;
import com.example.demo.entity.Message;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {PersonMapper.class})
public interface MessageMapper {

    @InheritInverseConfiguration
    Message toEntity(MessageDto dto);


    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "advertisementId", source = "advertisement.id")
    MessageDto toDto(Message entity);
}
