package com.example.demo_dynamodb.repository;

import com.example.demo_dynamodb.mapper.ProductMapper;
import com.example.demo_dynamodb.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    private final DynamoDbClient dynamoDbClient;
    private final ProductMapper productMapper;
    //The table name inside the Dynamodb
    @Value("${aws.dynamodb.table-name}")
    private String TABLE_NAME;

    public ProductRepository(DynamoDbClient dynamoDbClient, ProductMapper productMapper) {
        this.dynamoDbClient = dynamoDbClient;
        this.productMapper = productMapper;
    }

    public Product findById(String id) {

        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
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

        return productMapper.toProduct(item);
    }

    public Product save(Product product) {

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(productMapper.toItem(product))
                .build();

        dynamoDbClient.putItem(request);

        return product;
    }

    public List<Product> findAll() {
        ScanRequest request = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();

        return dynamoDbClient.scan(request)
                .items()
                .stream()
                .map(productMapper::toProduct)
                .toList();

    }

    public void deleteById(String id) {
        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(Map.of(
                        "id",
                        AttributeValue.builder()
                                .s(id)
                                .build()
                ))
                .build();

        dynamoDbClient.deleteItem(request);

    }

    public Product updateProduct(Product product) {
        UpdateItemRequest request = UpdateItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(Map.of(
                        "id", AttributeValue.builder()
                                .s(product.id())
                                .build()
                ))
                .updateExpression(
                        "SET #name = :name, " +
                                "description = :description, " +
                                "price = :price, " +
                                "category = :category, " +
                                "stock = :stock"
                )
                .expressionAttributeNames(Map.of(
                        "#name", "name"
                ))
                .expressionAttributeValues(
                        productMapper.toUpdateValues(product)
                )
                .returnValues(ReturnValue.ALL_NEW)
                .build();

        return productMapper.toProduct(
                dynamoDbClient.updateItem(request).attributes());
    }
}
