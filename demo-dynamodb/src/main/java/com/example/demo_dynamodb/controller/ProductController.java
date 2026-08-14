package com.example.demo_dynamodb.controller;

import com.example.demo_dynamodb.service.ProductService;
import org.springframework.web.bind.annotation.*;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Map<String, AttributeValue> findById(
            @PathVariable String id) {

        return productService.findById(id);
    }
}
