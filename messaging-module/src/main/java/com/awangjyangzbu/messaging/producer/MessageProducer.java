package com.awangjyangzbu.messaging.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Description: the message producer class, which is used to send messages to the message queue.
 */
@Service
public class MessageProducer {
    private final RabbitTemplate rabbitTemplate;
    private final String queueName;

    @Autowired
    public MessageProducer(RabbitTemplate rabbitTemplate,
                           @Value("${rabbitmq.queue.name}") String queueName) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(queueName, message);
        System.out.println("Sent message: " + message);
    }
}
