package com.webischia.api.configurations;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webischia.api.model.TestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class QueueConfiguration {
    Logger logger = LoggerFactory.getLogger(QueueConfiguration.class);

    private String queueUrl;
    private AmazonSQS sqs;

    public String getQueueUrl() {
        return queueUrl;
    }

    public void setQueueUrl(String queueUrl) {
        this.queueUrl = queueUrl;
    }

    @PostConstruct
    private void findQueueURL() {
        this.sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).build();
        setQueueUrl(sqs.getQueueUrl(new GetQueueUrlRequest().withQueueName("spring-test")).getQueueUrl()); //TODO read from properties
        logger.info(getQueueUrl());
    }

    public boolean sendMessage(TestModel model) throws Exception {
        if (getQueueUrl().isEmpty()) {
            throw new RuntimeException("Queue URL is not setted!");
        }
        ObjectMapper mapper = new ObjectMapper();
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(getQueueUrl())
                .withMessageBody(mapper.writeValueAsString(model))
                .withMessageAttributes(null); //TODO trace

        SendMessageResult res = sqs.sendMessage(request);
        return !res.getMessageId().isEmpty();
    }
}
