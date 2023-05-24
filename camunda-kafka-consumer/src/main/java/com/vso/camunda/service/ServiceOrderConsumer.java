package com.vso.camunda.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ServiceOrderConsumer {

//    @Autowired
//    TMFService tmfService;

    @KafkaListener(topics = "${spring.kafka.topics}", containerFactory = "kafkaListenerContainerFactory")
    public void consumeJson(String serviceOrderRequest) {
        if(serviceOrderRequest !=null && !serviceOrderRequest.trim().isEmpty()) {
            log.info("Parsing ServiceOrderRequest : {} into JSON", serviceOrderRequest);
            JSONObject serviceOrderRequestJson = new JSONObject(serviceOrderRequest);
            log.info("Order Id : {}",serviceOrderRequestJson.get("id"));
            //String output = tmfService.callTMFService(serviceOrderRequestJson.getString("id"));
        }
    }
}
