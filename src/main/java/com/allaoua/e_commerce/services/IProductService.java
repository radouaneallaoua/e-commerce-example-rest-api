package com.allaoua.e_commerce.services;


import com.allaoua.e_commerce.dtos.Product.ProductRequestDTO;
import com.allaoua.e_commerce.dtos.Product.ProductResponseDTO;
import com.allaoua.e_commerce.exceptions.CategoryNotFoundException;
import com.allaoua.e_commerce.exceptions.ProductNotFoundException;

import java.util.List;

public interface IProductService {
    ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO updateProduct(String productId,ProductRequestDTO productRequestDTO) throws ProductNotFoundException;
    String deleteProduct(String productId) throws ProductNotFoundException;
    ProductResponseDTO getProductById(String productId) throws ProductNotFoundException;
    List<ProductResponseDTO> getAllProducts();
    List<ProductResponseDTO> getAllProductsByCategoryId(Long categoryId) throws CategoryNotFoundException;

}
