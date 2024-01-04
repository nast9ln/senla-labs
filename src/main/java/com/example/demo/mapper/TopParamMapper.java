package com.example.demo.mapper;

import com.example.demo.dto.TopParamDto;
import com.example.demo.entity.TopParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TopParamMapper {
    TopParam toEntity(TopParamDto dto);

    TopParamDto toDto(TopParam entity);

    void update(TopParam exTopParam, @MappingTarget TopParam newTopParam);
}
