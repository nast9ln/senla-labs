package ru.labs.coffer.mapper;

import org.mapstruct.Mapper;
import ru.labs.coffer.dto.CategoryDto;
import ru.labs.coffer.entity.Category;

@Mapper
public interface CategoryMapper {
    Category toEntity(CategoryDto dto);

    CategoryDto toDto(Category entity);

}
