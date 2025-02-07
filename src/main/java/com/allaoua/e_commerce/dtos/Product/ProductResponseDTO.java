package com.allaoua.e_commerce.dtos.Product;

import com.allaoua.e_commerce.dtos.Category.CategoryResponseDTO;
import com.allaoua.e_commerce.entities.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private CategoryResponseDTO category;
}
