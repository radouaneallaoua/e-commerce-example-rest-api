package com.allaoua.e_commerce.services;


import com.allaoua.e_commerce.dtos.Category.CategoryRequestDTO;
import com.allaoua.e_commerce.dtos.Category.CategoryResponseDTO;
import com.allaoua.e_commerce.exceptions.CategoryNotFoundException;

import java.util.List;

public interface ICategoryService {
    CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO updateCategory(Long categoryId,CategoryRequestDTO categoryRequestDTO) throws CategoryNotFoundException;
    String deleteCategory(Long categoryId) throws CategoryNotFoundException;
    CategoryResponseDTO getCategoryById(Long categoryId) throws CategoryNotFoundException;
    List<CategoryResponseDTO> getAllCategories();
}
