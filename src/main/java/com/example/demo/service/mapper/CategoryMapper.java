package com.example.demo.service.mapper;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import jakarta.persistence.CacheRetrieveMode;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface CategoryMapper {
    Category toEntity (CategoryDto dto);
    CategoryDto toDto (Category entity);

}
