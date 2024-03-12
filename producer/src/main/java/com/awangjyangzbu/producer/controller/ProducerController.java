package com.awangjyangzbu.producer.controller;

import com.awangjyangzbu.producer.service.MessageProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    private final MessageProducerService messageProducerService;

    @Autowired
    public ProducerController(MessageProducerService messageProducerService) {
        this.messageProducerService = messageProducerService;
    }

    @GetMapping("/trigger")  // Endpoint matches the path used in TriggerController
    public ResponseEntity<Void> triggerMessageGeneration() {
        messageProducerService.publishMessages(100000);  // Modify message count as needed
        return ResponseEntity.ok().build();
    }
}

