package com.allaoua.e_commerce.dtos.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class CategoryRequestDTO {
    @NotBlank
    private String name;
}
