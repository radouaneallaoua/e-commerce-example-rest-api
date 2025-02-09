package com.allaoua.e_commerce.services;


import com.allaoua.e_commerce.dtos.Customer.CustomerRequestDTO;
import com.allaoua.e_commerce.dtos.Customer.CustomerResponseDTO;
import com.allaoua.e_commerce.exceptions.CustomerNotFoundException;

import java.util.List;

public interface ICustomerService {
    CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO updateCustomer(String customerId,CustomerRequestDTO customerRequestDTO) throws CustomerNotFoundException;
    String deleteCustomer(String customerId) throws CustomerNotFoundException;
    CustomerResponseDTO getCustomerById(String customerId) throws CustomerNotFoundException;
    List<CustomerResponseDTO> getAllCustomers();
}
