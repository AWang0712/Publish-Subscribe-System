package com.awangjyangzbu.producer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @Description: tests a case when there is an exception in the consumer
 * Sending a message to simple.queue.
 * And setup an exception in the consumer listening to the simple.queue
 * So the message will be sent to the dead letter queue: error.direct
 * */
@SpringBootTest
public class FailToConsumeTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description: test send message to simple queue, without specifying the exchange
     * */
    @Test
    public void testSendMsg2SimpleQueue() {
        String queueName = "simple.queue";
        String msg = "Hello, this msg will possibly fail to be consumed eventually!";

        rabbitTemplate.convertAndSend(queueName, msg);
    }
}
