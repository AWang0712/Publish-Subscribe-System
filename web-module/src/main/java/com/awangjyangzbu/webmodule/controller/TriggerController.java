package com.awangjyangzbu.webmodule.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
/**
* @Description: the controller to trigger the producer to send the messages.
**/
@RestController
public class TriggerController {

    // Hardcoded list of producer URLs (replace with your actual URLs)
    private static final List<String> PRODUCER_URLS = Arrays.asList(
            /* TODO: Environment Variables:
             Store the service names or URLs of your producer deployments as
             environment variables within web module container.
             You can set these environment variables during deployment using tools like kubectl.*/
            "http://producer-1:8080/producer-trigger",
            "http://producer-2:8080/producer-trigger",
            "http://producer-3:8080/producer-trigger",
            "http://producer-4:8080/producer-trigger",
            "http://producer-5:8080/producer-trigger"
            // Add more URLs as needed for producers
    );

    @GetMapping("/trigger")
    public String triggerProducers() {
        // Loop through producer URLs and send trigger requests
        for (String producerUrl : PRODUCER_URLS) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getForObject(producerUrl, String.class);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle exceptions appropriately, log errors etc.
                return "Error triggering producer: " + producerUrl;
            }
        }
        return "Producers Triggered!";
    }
}

