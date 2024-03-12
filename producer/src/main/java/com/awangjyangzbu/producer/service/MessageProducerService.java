package com.awangjyangzbu.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerService {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducerService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // TODO: Set the queue name
    @Value("${RABBITMQ_QUEUE_NAME}")
    private String queueName;

    // Generate a dynamic message with timestamp
    public String generateMessage() {
        long timestamp = System.currentTimeMillis();
        return "Dynamic message - " + timestamp;
    }

    public void publishMessages(int messageCount) {
        for (int i = 0; i < messageCount; i++) {
            String message = generateMessage();
            try {
                rabbitTemplate.convertAndSend(queueName, message);
                logger.info("Sent message: " + message);
            } catch (Exception e) {
                logger.error("Error sending message: " + message, e);
            }
        }
    }
}
