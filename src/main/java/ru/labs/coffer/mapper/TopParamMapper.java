package ru.labs.coffer.mapper;

import org.mapstruct.*;
import ru.labs.coffer.dto.TopParamDto;
import ru.labs.coffer.entity.TopParam;

@Mapper(componentModel = "spring")
public interface TopParamMapper {
    TopParam toEntity(TopParamDto dto);
    TopParamDto toDto(TopParam entity);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget TopParam exTopParam, TopParam newTopParam);
}
