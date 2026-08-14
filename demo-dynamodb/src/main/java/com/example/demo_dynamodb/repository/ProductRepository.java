package com.example.demo_dynamodb.repository;

import com.example.demo_dynamodb.model.Product;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;

import java.math.BigDecimal;
import java.util.Map;

@Repository
public class ProductRepository {

    private final DynamoDbClient dynamoDbClient;

    public ProductRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public Product findById(String id) {

        GetItemRequest request = GetItemRequest.builder()
                .tableName("Products")
                .key(Map.of(
                        "id",
                        AttributeValue.builder()
                                .s(id)
                                .build()
                ))
                .build();

        Map<String, AttributeValue> item =
                dynamoDbClient.getItem(request).item();

        if (item == null || item.isEmpty()) {
            return null;
        }

        return new Product(
                item.get("id").s(),
                item.get("name").s(),
                item.get("description").s(),
                new BigDecimal(item.get("price").n()),
                item.get("category").s(),
                Integer.valueOf(item.get("stock").n())
        );
    }
}
