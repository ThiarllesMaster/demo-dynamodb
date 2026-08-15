package com.example.demo_dynamodb.service;

import com.example.demo_dynamodb.model.Product;
import com.example.demo_dynamodb.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(String id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }
}
