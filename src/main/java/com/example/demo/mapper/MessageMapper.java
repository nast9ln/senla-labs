package com.example.demo.mapper;

import com.example.demo.dto.MessageDto;
import com.example.demo.entity.Message;
import org.mapstruct.Mapper;

@Mapper(uses = {PersonMapper.class})
public interface MessageMapper {
    Message toEntity(MessageDto dto);

    MessageDto toDto(Message entity);

}
