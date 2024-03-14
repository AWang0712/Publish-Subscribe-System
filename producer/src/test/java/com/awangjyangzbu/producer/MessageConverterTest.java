package com.awangjyangzbu.producer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class MessageConverterTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description: test send object to queue
     * About message converter in ProducerApplication class:
     *
     * without message converter, the object will be sent as byte array:
     * 178 bytes
     * Encoding: base64
     * rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAACdAAEbmFtZXQA
     * A0pvZXQAA2FnZXNyABFqYXZhLmxhbmcuSW50ZWdlchLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAAAABReA==
     *
     * with message converter, the object will be sent as json string:
     * 23 bytes
     * Encoding: string
     * {"name":"Joe","age":81}
     * */
    @Test
    public void testSendObject() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Joe");
        map.put("age", 81);
        rabbitTemplate.convertAndSend("object.queue", map);
    }
}
