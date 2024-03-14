package com.awangjyangzbu.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @Description: fanout exchange configuration, using beans
 * */
@Configuration
public class FanoutConfiguration {

    // fanout exchange
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("csen317.fanout");
    }

    // fanout queue 1 and 2
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("csen317.fanout.queue1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("csen317.fanout.queue2");
    }

    // binding fanout queue 1 and 2 to fanout exchange
    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
