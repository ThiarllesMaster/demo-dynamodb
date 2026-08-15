package com.example.demo_dynamodb.mapper;

import com.example.demo_dynamodb.model.Product;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductMapper {

    public Map<String, AttributeValue> toItem(Product product) {

        Map<String, AttributeValue> item = new HashMap<>();

        item.put("id", AttributeValue.builder()
                .s(product.id())
                .build());

        item.put("name", AttributeValue.builder()
                .s(product.name())
                .build());

        item.put("description", AttributeValue.builder()
                .s(product.description())
                .build());

        item.put("price", AttributeValue.builder()
                .n(product.price().toString())
                .build());

        item.put("category", AttributeValue.builder()
                .s(product.category())
                .build());

        item.put("stock", AttributeValue.builder()
                .n(product.stock().toString())
                .build());

        return item;
    }

    public Product toProduct(Map<String, AttributeValue> item) {

        return new Product(
                item.get("id").s(),
                item.get("name").s(),
                item.get("description").s(),
                new BigDecimal(item.get("price").n()),
                item.get("category").s(),
                Integer.valueOf(item.get("stock").n())
        );
    }

    public Map<String, AttributeValue> toUpdateValues(Product product) {
        return Map.of(
                ":name", AttributeValue.builder().s(product.name()).build(),
                ":description", AttributeValue.builder().s(product.description()).build(),
                ":price", AttributeValue.builder().n(product.price().toString()).build(),
                ":category", AttributeValue.builder().s(product.category()).build(),
                ":stock", AttributeValue.builder().n(product.stock().toString()).build()
        );
    }

}
