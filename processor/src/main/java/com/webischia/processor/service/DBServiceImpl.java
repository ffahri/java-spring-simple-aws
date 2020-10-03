package com.webischia.processor.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.webischia.processor.configurations.DynamoDBConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import testModel.model.TestModel;

import java.util.HashMap;

@Service
public class DBServiceImpl {

    private DynamoDBConfiguration dynamoDBConfiguration;
    private AmazonDynamoDB dynamoDB;
    @Value("${model.table.name}")
    private String tableName;

    public DBServiceImpl(DynamoDBConfiguration dynamoDBConfiguration) {
        this.dynamoDBConfiguration = dynamoDBConfiguration;
        this.dynamoDB = dynamoDBConfiguration.generateDynamoDBClient();
    }

    public void write(TestModel model) {
        HashMap<String, AttributeValue> itemMap = new HashMap<>(); //TODO use much more native DDB solution
        itemMap.put("modelId", new AttributeValue().withS(model.getId()));
        itemMap.put("name", new AttributeValue().withS(model.getName()));
        itemMap.put("value", new AttributeValue().withN(String.valueOf(model.getValue())));

        PutItemRequest putItemRequest = new PutItemRequest()
                .withTableName(tableName)
                .withItem(itemMap);
        dynamoDB.putItem(putItemRequest);
    }

}
