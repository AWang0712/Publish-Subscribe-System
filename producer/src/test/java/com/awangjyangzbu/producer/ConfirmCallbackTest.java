package com.awangjyangzbu.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;
/**
 * @Description: test confirm callback, which is used to confirm whether the message is sent successfully to the broker
 */
@Slf4j
@SpringBootTest
public class ConfirmCallbackTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testConfirmCallback() {
        // 1. create a CorrelationData object
        CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
        // 2. add confirm callback
        cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable ex) {// fail to call back, seldom happen
                log.error("ConfirmCallback.onFailure", ex);
            }
            @Override
            public void onSuccess(CorrelationData.Confirm result) {// success to call back
                log.debug("ConfirmCallback received");
                if(result.isAck()) {
                    log.debug("successfully send message to MQ, ack");
                } else {
                    log.debug("message fail to send to MQ, nack. Reason: {}", result.getReason());
                }
            }
        });
        // 3. send message

        // a success test case:
        // rabbitTemplate.convertAndSend("amq.direct", "red", "hello there", cd);
        // will receive ack

        // a failure test case: the routing key "ted" does not exist
        rabbitTemplate.convertAndSend("amq.direct", "ted", "hello there", cd);
        // will receive ack, and receive a return callback with reasons due to routing failure

        // a failure test case: the exchange "aww.direct" does not exist
        // rabbitTemplate.convertAndSend("aww.direct", "red", "hello there", cd);
        // will receive nack

        // 4. wait for the callback
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
