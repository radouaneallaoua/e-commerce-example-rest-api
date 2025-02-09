package com.allaoua.e_commerce.mappers;

import com.allaoua.e_commerce.dtos.Product.ProductRequestDTO;
import com.allaoua.e_commerce.dtos.Product.ProductResponseDTO;
import com.allaoua.e_commerce.entities.Product;
import com.allaoua.e_commerce.exceptions.CategoryNotFoundException;
import com.allaoua.e_commerce.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductMapper {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    public ProductMapper(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }


    public Product toEntity(ProductRequestDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .category(categoryRepository.findById(dto.getCategoryId()).orElseThrow(()->new CategoryNotFoundException("Category not found")))
                .build();
    }

    public ProductResponseDTO toDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .stock(product.getStock())
                .price(product.getPrice())
                .category(categoryMapper.toDTO(product.getCategory()))
                .build();
    }

    public List<ProductResponseDTO> toListOfDTOS(List<Product> products) {
        return products.stream().map(this::toDTO).toList();
    }
}
