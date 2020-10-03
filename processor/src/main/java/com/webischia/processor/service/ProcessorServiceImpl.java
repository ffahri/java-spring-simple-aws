package com.webischia.processor.service;

import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import testModel.model.TestModel;

@Component
@EnableSqs
@Configuration
public class ProcessorServiceImpl {

    private DBServiceImpl dbService;

    public ProcessorServiceImpl(DBServiceImpl dbService) {
        this.dbService = dbService;
    }

    @SqsListener(value = "${model.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    private void receiveMessage(TestModel msg) throws Exception {
        dbService.write(msg);
    }

}
