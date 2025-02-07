package com.allaoua.e_commerce.services.Impl;


import com.allaoua.e_commerce.dtos.Category.CategoryRequestDTO;
import com.allaoua.e_commerce.dtos.Category.CategoryResponseDTO;
import com.allaoua.e_commerce.entities.Category;
import com.allaoua.e_commerce.exceptions.CategoryNotFoundException;
import com.allaoua.e_commerce.mappers.CategoryMapper;
import com.allaoua.e_commerce.repositories.CategoryRepository;
import com.allaoua.e_commerce.services.ICategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ICategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public ICategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) {
        Category categoryToSave = categoryMapper.toEntity(categoryRequestDTO);
        Category savedCategory=categoryRepository.save(categoryToSave);
        return categoryMapper.toDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long categoryId,CategoryRequestDTO categoryRequestDTO) throws CategoryNotFoundException {
        categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
        Category categoryToUpdate = categoryMapper.toEntity(categoryRequestDTO);
        categoryToUpdate.setId(categoryId);
        Category updatedCategory=categoryRepository.save(categoryToUpdate);
        return categoryMapper.toDTO(updatedCategory);
    }

    @Override
    public String deleteCategory(Long categoryId) throws CategoryNotFoundException {
        categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
        categoryRepository.deleteById(categoryId);
        return "Category deleted successfully";
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long categoryId) throws CategoryNotFoundException {
        Optional<Category> category=categoryRepository.findById(categoryId);
        if(category.isEmpty())
            throw new CategoryNotFoundException("Category not found");
        return categoryMapper.toDTO(category.get());
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryMapper.toListOfDTOS(categoryRepository.findAll());
    }
}
