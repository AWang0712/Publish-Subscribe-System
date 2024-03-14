package com.awangjyangzbu.producer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
/**
 * @Description: tests for different types of exchanges
 * */
@SpringBootTest
public class ExchangeTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description: test send message to simple queue, without specifying the exchange
    * */
    @Test
    public void testSendMsg2Queue() {
        String queueName = "simple.queue";
        String msg = "Hello, Spring AMQP!";

        rabbitTemplate.convertAndSend(queueName, msg);
    }

    /**
     * @Description: test send message to fanout exchange
     * */
    @Test
    public void testSendMsg2FanoutExchange() {
        String exchangeName = "amq.fanout";
        String msg = "Hello everybody, I am using fanout exchange!";

        rabbitTemplate.convertAndSend(exchangeName, "", msg);
    }

    /**
     * @Description: test send message to direct exchange
     * */
    @Test
    public void testSendMsg2DirectExchangeKey1() {
        String exchangeName = "amq.direct";
        String routingKey = "red";
        String msg = "Hello there, both of you can receive this message!";

        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
        // expect to see the message received by both direct.queue1 and direct.queue2
    }

    @Test
    public void testSendMsg2DirectExchangeKey2() {
        String exchangeName = "amq.direct";
        String routingKey = "blue";
        String msg = "Hello there, only direct.queue1 can receive this message!";

        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
        // expect to see the message received by only direct.queue1
    }

    /**
     * @Description: test send message to topic exchange
     * */
    @Test
    public void testSendMsg2TopicExchangeKey1() {
        String exchangeName = "amq.topic";
        String routingKey = "ca.news";
        String msg = "news from California";

        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
        // expect to see the message received by topic.queue1 and topic.queue2
    }

    @Test
    public void testSendMsg2TopicExchangeKey2() {
        String exchangeName = "amq.topic";
        String routingKey = "us.news";
        String msg = "news from the United States";

        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
        // expect to see the message received by topic.queue2
    }
}
