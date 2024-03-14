package com.awangjyangzbu;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/**
 * ProducerApplication, the main class of the producer
 */
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    // use Jackson2JsonMessageConverter to convert message to json format
    @Bean
    public MessageConverter jacksonMessageConverter() {

        Jackson2JsonMessageConverter jjmc = new Jackson2JsonMessageConverter();
        // set createMessageIds to true to generate message ids to achieve idempotent
        jjmc.setCreateMessageIds(true);
        return jjmc;
    }

}
