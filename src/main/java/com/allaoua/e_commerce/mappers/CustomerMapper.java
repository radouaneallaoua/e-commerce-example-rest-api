package com.allaoua.e_commerce.mappers;

import com.allaoua.e_commerce.dtos.Customer.CustomerRequestDTO;
import com.allaoua.e_commerce.dtos.Customer.CustomerResponseDTO;
import com.allaoua.e_commerce.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequestDTO dto) {
        return Customer.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .build();
    }

    public CustomerResponseDTO toDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .build();
    }
    public List<CustomerResponseDTO> toListOfDTOS(List<Customer> customers) {
        return customers.stream().map(this::toDTO).toList();
    }
}
