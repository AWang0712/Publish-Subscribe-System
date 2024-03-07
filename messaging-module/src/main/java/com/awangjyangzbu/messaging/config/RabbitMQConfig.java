package com.awangjyangzbu.messaging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: the configuration of RabbitMQ.
 */

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", true);
    }

    // Add Exchange and Binding beans if needed
}
