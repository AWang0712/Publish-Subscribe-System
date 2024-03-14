package com.awangjyangzbu.producer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
/**
 * This test class sends 10k messages to simple.queue.
 * The PERSISTENT delivery mode ensures that the message is not lost even if the broker is restarted.
 * lazy queues are used to store messages on disk, so the broker can handle a large number of messages.
 */
@SpringBootTest
public class PersistenceTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testLazyQueue() {
        // persistent message
        Message message = MessageBuilder
                .withBody("floods".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .build();
        // send 10k messages
        for (int i = 0; i < 100000; i++) {
            rabbitTemplate.convertAndSend("simple.queue", message);
        }
    }
}
