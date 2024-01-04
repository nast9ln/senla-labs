package com.example.demo.service.mapper;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    Category toEntity(CategoryDto dto);

    CategoryDto toDto(Category entity);

}
