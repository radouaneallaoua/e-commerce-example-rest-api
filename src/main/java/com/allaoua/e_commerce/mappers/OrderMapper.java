package com.allaoua.e_commerce.mappers;

import com.allaoua.e_commerce.dtos.Order.OrderRequestDTO;
import com.allaoua.e_commerce.dtos.Order.OrderResponseDTO;
import com.allaoua.e_commerce.entities.Order;
import com.allaoua.e_commerce.enums.OrderStatus;
import com.allaoua.e_commerce.exceptions.CustomerNotFoundException;
import com.allaoua.e_commerce.repositories.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    private final CustomerRepository customerRepository;
    private final OrderProductMapper orderProductMapper;
    private final CustomerMapper customerMapper;
    public OrderMapper(CustomerRepository customerRepository, OrderProductMapper orderProductMapper, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.orderProductMapper = orderProductMapper;
        this.customerMapper = customerMapper;
    }


    public Order toEntity(OrderRequestDTO dto) {
        return Order.builder()
                .customer(customerRepository.findById(dto.getCustomerId()).orElseThrow(()-> new CustomerNotFoundException("Customer not found exception")))
                .status(OrderStatus.IN_PROGRESS)
                .build();
    }

    public OrderResponseDTO toDTO(Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .products(order.getProducts()==null?null: orderProductMapper.toListOfDTOS(order.getProducts()))
                .status(order.getStatus())
                .date(order.getDate())
                .customer(customerMapper.toDTO(order.getCustomer()))
                .build();
    }

    public List<OrderResponseDTO> toListOfDTOS(List<Order> orders) {
        return orders.stream().map(this::toDTO).toList();
    }

}
