package com.allaoua.e_commerce.mappers;

import com.allaoua.e_commerce.dtos.OrderProduct.OrderProductRequestDTO;
import com.allaoua.e_commerce.dtos.OrderProduct.OrderProductResponseDTO;
import com.allaoua.e_commerce.entities.Order;
import com.allaoua.e_commerce.entities.OrderProduct;
import com.allaoua.e_commerce.exceptions.CategoryNotFoundException;
import com.allaoua.e_commerce.exceptions.ProductNotFoundException;
import com.allaoua.e_commerce.repositories.CategoryRepository;
import com.allaoua.e_commerce.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderProductMapper {
    private final ProductRepository productRepository;

    public OrderProductMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public  OrderProduct toEntity(OrderProductRequestDTO dto) {
        return OrderProduct.builder()
                .product(productRepository.findById(dto.getProductId()).orElseThrow(()-> new ProductNotFoundException("product not found")))
                .quantity(dto.getQuantity())
                .build();
    }

    public OrderProductResponseDTO toDTO(OrderProduct orderProduct) {
        return OrderProductResponseDTO.builder()
                .orderId(orderProduct.getId().getOrderId())
                .productId(orderProduct.getId().getProductId())
                .quantity(orderProduct.getQuantity())
                .build();
    }



    public List<OrderProductResponseDTO> toListOfDTOS(List<OrderProduct> orderProducts) {
        return orderProducts.stream().map(this::toDTO).toList();
    }
}
