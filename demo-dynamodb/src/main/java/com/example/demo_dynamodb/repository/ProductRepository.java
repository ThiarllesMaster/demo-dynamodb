package com.example.demo_dynamodb.repository;

import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;

import java.util.Map;

@Repository
public class ProductRepository {

    private final DynamoDbClient dynamoDbClient;

    public ProductRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public Map<String, AttributeValue> findById(String id) {

        GetItemRequest request = GetItemRequest.builder()
                .tableName("Products")
                .key(Map.of(
                        "id",
                        AttributeValue.builder()
                                .s(id)
                                .build()
                ))
                .build();

        return dynamoDbClient
                .getItem(request)
                .item();
    }
}
