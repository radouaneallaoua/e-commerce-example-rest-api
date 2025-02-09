package com.allaoua.e_commerce.dtos.Order;

import com.allaoua.e_commerce.dtos.OrderProduct.OrderProductRequestDTO;
import com.allaoua.e_commerce.entities.OrderProduct;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class OrderRequestDTO {
    private String customerId;
    private List<OrderProductRequestDTO> products;
}
