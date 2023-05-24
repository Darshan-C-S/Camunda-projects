package com.vso.camunda.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ServiceOrderProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String kafkaTopic;

    public void sendJson() {
        String serviceOrderRequest = "{ \"id\": \"12345\", \"name\": \"Service Order Request\" }";
        kafkaTemplate.send(kafkaTopic, serviceOrderRequest);
    }
}
