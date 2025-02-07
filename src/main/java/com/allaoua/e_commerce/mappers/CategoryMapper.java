package com.allaoua.e_commerce.mappers;

import com.allaoua.e_commerce.dtos.Category.CategoryRequestDTO;
import com.allaoua.e_commerce.dtos.Category.CategoryResponseDTO;
import com.allaoua.e_commerce.entities.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequestDTO dto) {
        return Category.builder()
                .name(dto.getName())
                .build();
    }

    public CategoryResponseDTO toDTO(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
    public List<CategoryResponseDTO> toListOfDTOS(List<Category> categories) {
        return categories.stream().map(this::toDTO).toList();
    }
}
