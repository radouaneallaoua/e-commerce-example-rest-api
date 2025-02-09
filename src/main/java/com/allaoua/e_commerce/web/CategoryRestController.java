package com.allaoua.e_commerce.web;

import com.allaoua.e_commerce.dtos.Category.CategoryRequestDTO;
import com.allaoua.e_commerce.dtos.Category.CategoryResponseDTO;
import com.allaoua.e_commerce.services.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
    private final ICategoryService categoryService;

    public CategoryRestController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping("/categories")
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }


    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO saveCategory(@Valid @RequestBody CategoryRequestDTO category) {
        return categoryService.saveCategory(category);
    }
    
    
    @GetMapping("/categories/{categoryId}")
    public CategoryResponseDTO getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PutMapping("/categories/{categoryId}")

    public CategoryResponseDTO updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryRequestDTO category) {
        return categoryService.updateCategory(categoryId, category);
    }

    @DeleteMapping("/categories/{categoryId}")
    public String deleteCategoryById(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
    
    
    
}
