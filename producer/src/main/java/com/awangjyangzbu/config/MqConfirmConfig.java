package com.awangjyangzbu.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
/**
 * @Description: configuration for message confirm
 * publish return callback
 * when the message is <not routed to any queue>, the return callback will be triggered
 * */
@Slf4j
@Configuration
public class MqConfirmConfig implements ApplicationContextAware{

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        // configure the return callback
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                log.debug("Returned callback, exchange: {}, routingKey: {}, replyCode: {}, replyText: {}, message: {}",
                        returned.getExchange(), returned.getRoutingKey(), returned.getReplyCode(), returned.getReplyText(), new String(returned.getMessage().getBody()));
            }
        });

    }
}