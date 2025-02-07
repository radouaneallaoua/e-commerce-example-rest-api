package com.allaoua.e_commerce.dtos.Category;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoryResponseDTO {
    private Long id;
    private String name;
}
