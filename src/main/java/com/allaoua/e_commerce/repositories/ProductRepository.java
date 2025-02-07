package com.allaoua.e_commerce.repositories;

import com.allaoua.e_commerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findByCategoryId(Long categoryId);
}
