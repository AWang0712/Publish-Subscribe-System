package com.awangjyangzbu.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: the message consumer to receive the message from the queue.
 */
@Component
public class MessageConsumer {

    @RabbitListener(queues = "#{rabbitMQConfig.myQueue().name}")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
