package com.awangjyangzbu.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: direct exchange configuration, using beans
 * */
@Configuration
public class DirectConfiguration {

    // direct exchange
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("csen317.direct");
    }

    // direct queue 1 and 2
    @Bean
    public Queue directQueue1() {
        return new Queue("csen317.direct.queue1");
    }

    @Bean
    public Queue directQueue2() {
        return new Queue("csen317.direct.queue2");
    }

    // binding direct queue 1 and 2 to direct exchange
    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with("red");
    }

    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with("blue");
    }

    @Bean
    public Binding directBinding3() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("blue");
    }

    @Bean
    public Binding directBinding4() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("green");
    }
}
