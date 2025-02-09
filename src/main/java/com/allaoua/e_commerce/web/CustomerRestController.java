package com.allaoua.e_commerce.web;

import com.allaoua.e_commerce.dtos.Customer.CustomerRequestDTO;
import com.allaoua.e_commerce.dtos.Customer.CustomerResponseDTO;
import com.allaoua.e_commerce.services.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {
    private final ICustomerService customerService;

    public CustomerRestController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDTO saveCustomer(@Valid @RequestBody CustomerRequestDTO customer) {
        return customerService.saveCustomer(customer);
    }


    @GetMapping("/customers/{customerId}")
    public CustomerResponseDTO getCustomerById(@PathVariable String customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PutMapping("/customers/{customerId}")
    public CustomerResponseDTO updateCustomer(@PathVariable String customerId, @Valid @RequestBody CustomerRequestDTO customer) {
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomerById(@PathVariable String customerId) {
        return customerService.deleteCustomer(customerId);
    }

}
