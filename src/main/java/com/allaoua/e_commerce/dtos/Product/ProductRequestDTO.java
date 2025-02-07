package com.allaoua.e_commerce.dtos.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDTO {
    @NotBlank
    private String name;
    private String description;
    @Min(0)
    private double price;
    @Min(0)
    private int quantity;
    @NotNull
    private Long categoryId;
}
