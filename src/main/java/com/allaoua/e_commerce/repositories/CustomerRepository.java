package com.allaoua.e_commerce.repositories;

import com.allaoua.e_commerce.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,String> {
    Optional<Customer> findByEmail(String email);
}
