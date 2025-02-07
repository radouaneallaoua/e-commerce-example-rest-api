package com.allaoua.e_commerce.services.Impl;

import com.allaoua.e_commerce.dtos.Product.ProductRequestDTO;
import com.allaoua.e_commerce.dtos.Product.ProductResponseDTO;
import com.allaoua.e_commerce.entities.Product;
import com.allaoua.e_commerce.exceptions.CategoryNotFoundException;
import com.allaoua.e_commerce.exceptions.ProductNotFoundException;
import com.allaoua.e_commerce.mappers.ProductMapper;
import com.allaoua.e_commerce.repositories.CategoryRepository;
import com.allaoua.e_commerce.repositories.ProductRepository;
import com.allaoua.e_commerce.services.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public IProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO) {
        Product productToSave=productMapper.toEntity(productRequestDTO);
        Product savedProduct=productRepository.save(productToSave);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(String productId,ProductRequestDTO productRequestDTO) throws ProductNotFoundException {
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("product not found"));
        Product productToUpdate=productMapper.toEntity(productRequestDTO);
        productToUpdate.setId(productId);
        Product updatedProduct=productRepository.save(productToUpdate);
        return productMapper.toDTO(updatedProduct);
    }

    @Override
    public String deleteProduct(String productId) throws ProductNotFoundException {
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("product not found"));
        productRepository.deleteById(productId);
        return "Product deleted successfully";
    }

    @Override
    public ProductResponseDTO getProductById(String productId) throws ProductNotFoundException {
        Optional<Product> product=productRepository.findById(productId);
        if(product.isEmpty())
            throw new ProductNotFoundException("product not found");
        return productMapper.toDTO(product.get());
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return  productMapper.toListOfDTOS(productRepository.findAll());
    }

    @Override
    public List<ProductResponseDTO> getAllProductsByCategoryId(Long categoryId) throws CategoryNotFoundException {
        categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("category not found"));
        return productMapper.toListOfDTOS(productRepository.findByCategoryId(categoryId));
    }
}
