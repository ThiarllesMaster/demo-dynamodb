package com.example.demo_dynamodb.service;

import com.example.demo_dynamodb.repository.ProductRepository;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Map<String, AttributeValue> findById(String id) {
        return productRepository.findById(id);
    }
}