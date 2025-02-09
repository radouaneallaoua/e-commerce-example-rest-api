package com.allaoua.e_commerce.dtos.Order;

import com.allaoua.e_commerce.dtos.Customer.CustomerResponseDTO;
import com.allaoua.e_commerce.dtos.OrderProduct.OrderProductResponseDTO;
import com.allaoua.e_commerce.enums.OrderStatus;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class OrderResponseDTO {
    private String id;
    private OrderStatus status;
    private Date date;
    private List<OrderProductResponseDTO> products;
    private CustomerResponseDTO customer;
}
