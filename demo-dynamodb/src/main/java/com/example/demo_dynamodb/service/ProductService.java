package com.example.demo_dynamodb.service;

import com.example.demo_dynamodb.model.Product;
import com.example.demo_dynamodb.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(String id) {
        return productRepository.findById(id);
    }
}
