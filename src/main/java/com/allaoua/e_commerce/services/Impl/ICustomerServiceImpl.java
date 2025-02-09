package com.allaoua.e_commerce.services.Impl;

import com.allaoua.e_commerce.dtos.Customer.CustomerRequestDTO;
import com.allaoua.e_commerce.dtos.Customer.CustomerResponseDTO;
import com.allaoua.e_commerce.entities.Customer;
import com.allaoua.e_commerce.exceptions.CustomerNotFoundException;
import com.allaoua.e_commerce.mappers.CustomerMapper;
import com.allaoua.e_commerce.repositories.CustomerRepository;
import com.allaoua.e_commerce.services.ICustomerService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ICustomerServiceImpl implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public ICustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @CacheEvict(value = "customers", allEntries = true)
    @Override
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(customerRequestDTO.getEmail());
        if (foundCustomer.isPresent()) {
            throw  new CustomerNotFoundException("email already exists");
        }
        Customer customerToSave = customerMapper.toEntity(customerRequestDTO);
        Customer savedCustomer = customerRepository.save(customerToSave);
        return customerMapper.toDTO(savedCustomer);
    }

    @CacheEvict(value = {"customers","customer"},key = "#customerId", allEntries = true)
    @Override
    public CustomerResponseDTO updateCustomer(String customerId, CustomerRequestDTO customerRequestDTO) throws CustomerNotFoundException {
        customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("customer not found"));
        Customer customerToUpdate = customerMapper.toEntity(customerRequestDTO);
        customerToUpdate.setId(customerId);
        return customerMapper.toDTO(customerRepository.save(customerToUpdate));
    }

    @CacheEvict(value = "customers", allEntries = true)
    @Override
    public String deleteCustomer(String customerId) throws CustomerNotFoundException {
        customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("customer not found"));
        customerRepository.deleteById(customerId);
        return "Customer deleted successfully";
    }


    @Cacheable(value = "customer")
    @Override
    public CustomerResponseDTO getCustomerById(String customerId) throws CustomerNotFoundException {
        Optional<Customer> foundCustomer=customerRepository.findById(customerId);
        if (foundCustomer.isEmpty())
            throw new CustomerNotFoundException("customer not found");
        return customerMapper.toDTO(foundCustomer.get());
    }

    @Cacheable(value = "customers")
    @Override
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerMapper.toListOfDTOS(customerRepository.findAll());
    }
}
