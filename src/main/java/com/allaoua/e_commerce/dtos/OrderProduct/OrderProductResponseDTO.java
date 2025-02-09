package com.allaoua.e_commerce.dtos.OrderProduct;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductResponseDTO {
    private String productId;
    private String orderId;
    private int quantity;
}
