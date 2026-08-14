package com.example.demo_dynamodb.model;

import java.math.BigDecimal;

public record Product(
        String id,
        String name,
        String description,
        BigDecimal price,
        String category,
        Integer stock
) {
}
