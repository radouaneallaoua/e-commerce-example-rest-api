package com.allaoua.e_commerce.dtos.OrderProduct;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductRequestDTO {
    @NotBlank
    private String productId;
    @NotNull
    @Min(1)
    private int quantity;
}
