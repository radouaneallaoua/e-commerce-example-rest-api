package com.allaoua.e_commerce.web;

import com.allaoua.e_commerce.dtos.Product.ProductRequestDTO;
import com.allaoua.e_commerce.dtos.Product.ProductResponseDTO;
import com.allaoua.e_commerce.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
    private final IProductService productService;

    public ProductRestController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductResponseDTO> getAllProducts(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size ) {
        return productService.getAllProducts(page,size);
    }

    @GetMapping("/products/by-category/{categoryId}")
    public List<ProductResponseDTO> getProductsByCategoryId(@PathVariable Long categoryId) {
        return productService.getAllProductsByCategoryId(categoryId);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO saveProduct(@Valid @RequestBody ProductRequestDTO product) {
        return productService.saveProduct(product);
    }


    @GetMapping("/products/{productId}")
    public ProductResponseDTO getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/products/{productId}")
    public ProductResponseDTO updateProduct(@PathVariable String productId, @Valid @RequestBody ProductRequestDTO product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProductById(@PathVariable String productId) {
        return productService.deleteProduct(productId);
    }

}
