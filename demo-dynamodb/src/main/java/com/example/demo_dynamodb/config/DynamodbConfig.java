package com.example.demo_dynamodb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamodbConfig {

    @Value("${aws.region}")
    private String regionAWS;

    @Bean
    public DynamoDbClient dynamoDbClient() {
        DynamoDbClient client = DynamoDbClient.builder()
                .region(Region.of(regionAWS))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        return client;
    }
}
