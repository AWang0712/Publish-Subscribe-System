package com.awangjyangzbu.consumer.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: message queue listener
 * */
@Component
public class MqListener {

    /**
    * @Description: test simple queue
    * @param msg
    * */
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String msg) {

        System.out.println("Received message from simple.queue: " + msg);
        // to test the retry mechanism and dead letter queue
        throw new RuntimeException("test error queue");
    }

    /**
     * @Description: test fanout exchange
     * @param msg
     */
    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) {

        System.out.println("Received message from fanout.queue1: " + msg);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) {

        System.out.println("Received message from fanout.queue2: " + msg);
    }

    /**
     * @Description: test direct exchange
     * @param msg
     */
    @RabbitListener(queues = "direct.queue1")
    public void listenDirectQueue1(String msg) {

        System.out.println("Received message from direct.queue1: " + msg);
    }

    @RabbitListener(queues = "direct.queue2")
    public void listenDirectQueue2(String msg) {

        System.out.println("Received message from direct.queue2: " + msg);
    }

    /**
     * @Description: test topic exchange
     * @param msg
     */
    @RabbitListener(queues = "topic.queue1")
    public void listenTopicQueue1(String msg) {

        System.out.println("Received message from topic.queue1: " + msg);
    }

    @RabbitListener(queues = "topic.queue2")
    public void listenTopicQueue2(String msg) {

        System.out.println("Received message from topic.queue2: " + msg);
    }


}
