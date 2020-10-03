package com.webischia.api.services;

import com.webischia.api.configurations.QueueConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import testModel.model.TestModel;

@Component
public class TestModelServiceImpl implements TestModelService {
    Logger logger = LoggerFactory.getLogger(TestModelServiceImpl.class);
    QueueConfiguration queueConfiguration;

    public TestModelServiceImpl(QueueConfiguration queueConfiguration) {
        this.queueConfiguration = queueConfiguration;
    }

    @Override
    public boolean sendQueue(TestModel model) {
        logger.info(model.getName());
        try {
            return queueConfiguration.sendMessage(model);
        } catch (Exception e) {
            //TODO cleanup
            logger.error(e.getMessage());
        }
        return false;
    }
}
